package invmod;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;

public interface IBlockAccessExtended extends IBlockAccess {
	
	public int getLayeredData(int paramInt1, int paramInt2, int paramInt3);
	
	public default int getLayeredData(double x, double y, double z){
		return this.getLayeredData(MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z));
	}
	
	public default int getLayeredData(Vec3d vec){
		return this.getLayeredData(vec.xCoord, vec.yCoord, vec.zCoord);
	}
	
	public void setData(double x, double y, double z, Integer paramInteger);
	
}