package invmod.entity.ai;

import invmod.entity.Goal;
import invmod.entity.INavigationFlying;
import invmod.entity.monster.EntityIMFlying;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAICircleTarget extends EntityAIBase {
	private static final int ATTACK_SEARCH_TIME = 400;
	private EntityIMFlying theEntity;
	private int time;
	private int patienceTime;
	private int patience;
	private float preferredHeight;
	private float preferredRadius;

	public EntityAICircleTarget(EntityIMFlying entity, int patience, float preferredHeight, float preferredRadius) {
		this.theEntity = entity;
		this.time = 0;
		this.patienceTime = 0;
		this.patience = patience;
		this.preferredHeight = preferredHeight;
		this.preferredRadius = preferredRadius;
	}

	@Override
	public boolean shouldExecute() {
		return (this.theEntity.getAIGoal() == Goal.STAY_AT_RANGE) && (this.theEntity.getAttackTarget() != null);
	}

	@Override
	public boolean shouldContinueExecuting() {
		return ((this.theEntity.getAIGoal() == Goal.STAY_AT_RANGE) || (this.isWaitingForTransition()))
				&& (this.theEntity.getAttackTarget() != null);
	}

	@Override
	public void startExecuting() {
		INavigationFlying nav = this.theEntity.getNavigatorNew();
		nav.setMovementType(INavigationFlying.MoveType.PREFER_FLYING);
		nav.setCirclingPath(this.theEntity.getAttackTarget(), this.preferredHeight, this.preferredRadius);
		this.time = 0;
		int extraTime = (int) (4.0F * nav.getDistanceToCirclingRadius());
		if (extraTime < 0) {
			extraTime = 0;
		}
		this.patienceTime = (extraTime + this.theEntity.world.rand.nextInt(this.patience) + this.patience / 3);
	}

	@Override
	public void updateTask() {
		this.time += 1;
		if (this.theEntity.getAIGoal() == Goal.STAY_AT_RANGE) {
			this.patienceTime -= 1;
			if (this.patienceTime <= 0) {
				this.theEntity.transitionAIGoal(Goal.FIND_ATTACK_OPPORTUNITY);
				this.patienceTime = 400;
			}
		} else if (this.isWaitingForTransition()) {
			this.patienceTime -= 1;
			if (this.patienceTime > 0)
				;
		}
	}

	protected boolean isWaitingForTransition() {
		return (this.theEntity.getPrevAIGoal() == Goal.STAY_AT_RANGE)
				&& (this.theEntity.getAIGoal() == Goal.FIND_ATTACK_OPPORTUNITY);
	}
}