package warfake.war.battlefield;

import java.util.LinkedList;

import warfake.war.abstraction.Archer;
import warfake.war.abstraction.Mage;
import warfake.war.abstraction.Warrior;
import warfake.war.armedforce.ElfArcher;
import warfake.war.armedforce.ElfMage;
import warfake.war.armedforce.ElfWarrior;

public class ElfSquadFactory extends Squad {

	private ElfSquadFactory(LinkedList<Mage> elfMages, LinkedList<Archer> elfArchers, LinkedList<Warrior> elfWarriors) {
		super(elfMages, elfArchers, elfWarriors);
	}

	public static ElfSquadFactory generateElfSquad() {
		LinkedList<Mage> elfMages = new LinkedList<>();
		LinkedList<Archer> elfArchers = new LinkedList<>();
		LinkedList<Warrior> elfWarriors = new LinkedList<>();

		for (int i = 0; i < getNumberOfMages(); i++) {
			elfMages.add(new ElfMage());
		}
		for (int i = 0; i < getNumberOfArchers(); i++) {
			elfArchers.add(new ElfArcher());
		}
		for (int i = 0; i < getNumberOfWarriors(); i++) {
			elfWarriors.add(new ElfWarrior());
		}
		return new ElfSquadFactory(elfMages, elfArchers, elfWarriors);
	}
}
