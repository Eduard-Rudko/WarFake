package warfake.war.armedforce;

import java.util.Random;

import warefake.war.markers.Aliance;
import warefake.war.markers.Improvable;
import warfake.war.armory.ElfWeapons;
import warfake.war.battlefield.ElfSquadFactory;
import warfake.war.battlefield.OrcSquadFactory;
import warfake.war.battlefield.Squad;
import warfake.war.classes.and.races.Archer;
import warfake.war.classes.and.races.Person;

public class ElfArcher extends Person implements Archer, Aliance, Improvable {
	private static final float SHOT_POWER = 15;
	private static final float STAB_POWER = 2;
	private static ElfWeapons elvenBow = ElfWeapons.ELVEN_BOW;
	private static ElfWeapons elvenDagger = ElfWeapons.ELVEN_DAGGER;
	private static final int NUMBER_OF_SKILLS = 2;
	private static int id = 1;
	private int name = id++;

	public ElfArcher() {
		setName("Elf Archer " + name);
	}
	
	@Override
	public void archeryShot(Squad targets) {
		Person target = targets.getRandomTarget();
		int accuracy = getRandomAccuracy();
		dealDamage(target, SHOT_POWER, accuracy);
		logStrikeAction(getName(), elvenBow.getWeaponAction(), target, SHOT_POWER, accuracy);
	}

	@Override
	public void meleeStab(Squad targets) {
		Person target = targets.getRandomTarget();
		int accuracy = 100;
		dealDamage(target, STAB_POWER,accuracy);
		logStrikeAction(getName(), elvenDagger.getWeaponAction(), target, STAB_POWER, accuracy);
	}
	
	@Override
	public void performRandomAction(Squad aliance, Squad horde) {
		Random rnd = new Random();
		switch (rnd.nextInt(NUMBER_OF_SKILLS) + 1) {
		case 1:
			archeryShot(horde);
			break;
		case 2:
			meleeStab(horde);
			break;
		}
	}

	public static void main(String[] args) {
		Squad squad = OrcSquadFactory.generateOrcSquad();
		Squad squad1 = ElfSquadFactory.generateElfSquad();
		ElfArcher elf1 = new ElfArcher();
		elf1.performRandomAction(squad, squad1);
		elf1.performRandomAction(squad, squad1);
		elf1.performRandomAction(squad, squad1);
		elf1.performRandomAction(squad, squad1);
	}
}
