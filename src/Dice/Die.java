package Dice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Die {
	public enum Flags {
		CRIT,
		ADV,
		DIS,
		GWF,
		NO,
		SKIP_LOG;
	}

	public int amount;
	public int value;

	public Die(int amount, int value) {
		this.amount = amount;
		this.value = value;
	}

	public int rollDie(Flags... flagInputs) {
		StringBuilder sb = new StringBuilder();
		List<Flags> flags = Arrays.asList(flagInputs);
		int result = 0;
		int amountOfDie = flags.contains(Flags.CRIT) ? this.amount * 2 : this.amount;
		if (this.value == 1) {
			return amountOfDie;
		}
		sb.append(this.amount + "d" + this.value + " = ");
		for (int i = 0; i < amountOfDie; i++) {
			Random rng = new Random();
			int rollResult = rng.nextInt(this.value) + 1;
			sb.append(rollResult);
			if (flags.contains(Flags.ADV)) {
				int advantageRollResult = rng.nextInt(this.value) + 1;
				rollResult = Math.max(rollResult, advantageRollResult);
				sb.append("adv" + advantageRollResult);
			} else if (flags.contains(Flags.DIS)) {
				int disadvantageRollResult = rng.nextInt(this.value) + 1;
				rollResult = Math.min(rollResult, disadvantageRollResult);
				sb.append("dis" + disadvantageRollResult);
			} else if (flags.contains(Flags.GWF)) {
				if (rollResult <= 2) {
					rollResult = rng.nextInt(this.value) + 1;
					sb.append("r" + rollResult);
				}
			}
			sb.append(i == amountOfDie - 1 ? "" : "+");
			result += rollResult;
		}
		sb.append(" = " + result);
		if (!flags.contains(Flags.SKIP_LOG)) {
			System.out.println(sb.toString());
		}
		return result;
	}
}
