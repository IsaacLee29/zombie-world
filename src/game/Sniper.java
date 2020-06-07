package game;

public class Sniper extends RangeWeapon{
	
	public Sniper() {
		super("Sniper", 'S', 45, "Biang");
		this.addCapability(RangeWeaponCapabilities.SNIPER_AIMING);
	}
	
	public boolean hasCapability(Enum<?> capability) {
		return capabilities.hasCapability(capability);
	}
	
}
