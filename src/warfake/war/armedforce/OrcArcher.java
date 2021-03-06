package warfake.war.armedforce;

import java.util.Random;

import warfake.exeptions.NoEnemiesException;
import warfake.markers.Horde;
import warfake.markers.Improvable;
import warfake.war.abstraction.Archer;
import warfake.war.abstraction.Person;
import warfake.war.armory.OrcWeapons;
import warfake.war.battlefield.Squad;
import warfake.war.game.Game;

public class OrcArcher extends Person implements Archer, Horde, Improvable {
	private static final float SHOT_POWER = 17;
	private static final float STRIKE_POWER = 2;
	private static OrcWeapons bow = OrcWeapons.BOW;
	private static OrcWeapons axe = OrcWeapons.AXE;
	private static final int NUMBER_OF_SKILLS = 2;
	private static int id = 1;
	private int name = id++;

	public OrcArcher() {
		setName("Orc Archer " + name);
	}

	@Override
	public void archeryShot(Squad targets) {
		try {
			Person target = targets.getRandomTarget();
			int accuracy = getRandomAccuracy();
			dealDamage(target, SHOT_POWER, accuracy);
			logCurrentBonuses(getAccuracy(), SHOT_POWER);
			logStrikeAction(getName(), bow.getWeaponAction(), target, SHOT_POWER, accuracy);
			if (target.isDead()) {
				targets.removePerson(target);
			}
		} catch (NoEnemiesException e) {
			logHordeVictory();
			Game.createResults();
			Game.gameProcess = false;
			System.exit(0);
		}

	}

	@Override
	public void meleeStab(Squad targets) {
		try {
			Person target = targets.getRandomTarget();
			int accuracy = getMaxAccuracy();
			dealDamage(target, STRIKE_POWER, accuracy);
			logCurrentBonuses(getAccuracy(), STRIKE_POWER);
			logStrikeAction(getName(), axe.getWeaponAction(), target, STRIKE_POWER, accuracy);
			if (target.isDead()) {
				targets.removePerson(target);
			}
		} catch (NoEnemiesException e) {
			logHordeVictory();
			Game.gameProcess = false;
			Game.createResults();
			System.exit(0);
		}
	}

	@Override
	public void performRandomAction(Squad aliance, Squad horde) {
		Random rnd = new Random();
		switch (rnd.nextInt(NUMBER_OF_SKILLS) + 1) {
		case 1:
			archeryShot(aliance);
			break;
		case 2:
			meleeStab(aliance);
			break;
		}
	}
}
