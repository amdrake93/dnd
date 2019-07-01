package Dice;

import java.util.HashMap;
import java.util.Map;

import Dice.Die.Flags;

public class DieTest {
	private static final int iterations = 1000000;

	public static void main(String[] args) {
		Die d4 = new Die(1, 4);
		Die d6 = new Die(2, 6);
		Die d8 = new Die(1, 8);
		Die d10 = new Die(1, 10);
		Die d12 = new Die(1, 12);
		Die d20 = new Die(1, 20);

		Map<Integer, Long> d4Results = new HashMap<>();
		Map<Integer, Long> d6Results = new HashMap<>();
		Map<Integer, Long> d8Results = new HashMap<>();
		Map<Integer, Long> d10Results = new HashMap<>();
		Map<Integer, Long> d12Results = new HashMap<>();
		Map<Integer, Long> d20Results = new HashMap<>();

		for (int i = 0; i < iterations; i++) {
			Integer d4Result = d4.rollDie(Flags.SKIP_LOG, Flags.GWF);
			Long d4ResultFreq = d4Results.get(d4Result);
			d4ResultFreq = d4ResultFreq == null ? 1 : d4ResultFreq + 1;
			d4Results.put(d4Result, d4ResultFreq);

			Integer d6Result = d6.rollDie(Flags.SKIP_LOG, Flags.GWF);
			Long d6ResultFreq = d6Results.get(d6Result);
			d6ResultFreq = d6ResultFreq == null ? 1 : d6ResultFreq + 1;
			d6Results.put(d6Result, d6ResultFreq);

			Integer d8Result = d8.rollDie(Flags.SKIP_LOG, Flags.GWF);
			Long d8ResultFreq = d8Results.get(d8Result);
			d8ResultFreq = d8ResultFreq == null ? 1 : d8ResultFreq + 1;
			d8Results.put(d8Result, d8ResultFreq);

			Integer d10Result = d10.rollDie(Flags.SKIP_LOG, Flags.GWF);
			Long d10ResultFreq = d10Results.get(d10Result);
			d10ResultFreq = d10ResultFreq == null ? 1 : d10ResultFreq + 1;
			d10Results.put(d10Result, d10ResultFreq);

			Integer d12Result = d12.rollDie(Flags.SKIP_LOG, Flags.GWF);
			Long d12ResultFreq = d12Results.get(d12Result);
			d12ResultFreq = d12ResultFreq == null ? 1 : d12ResultFreq + 1;
			d12Results.put(d12Result, d12ResultFreq);

			Integer d20Result = d20.rollDie(Flags.SKIP_LOG, Flags.GWF);
			Long d20ResultFreq = d20Results.get(d20Result);
			d20ResultFreq = d20ResultFreq == null ? 1 : d20ResultFreq + 1;
			d20Results.put(d20Result, d20ResultFreq);
		}
		displayFreq(d4Results);
		System.out.println("\n");
		displayFreq(d6Results);
		System.out.println("\n");
		displayFreq(d8Results);
		System.out.println("\n");
		displayFreq(d10Results);
		System.out.println("\n");
		displayFreq(d12Results);
		System.out.println("\n");
		displayFreq(d20Results);
	}

	public static void displayFreq(Map<Integer, Long> resultToFreq) {
		for (Map.Entry<Integer, Long> entry : resultToFreq.entrySet()) {
			Integer value = entry.getKey();
			StringBuilder sb = new StringBuilder(value + ":\t");
			Long freqNormalizedToPercent = Math.round(entry.getValue() / 1000000d * 100);
			for (int i = 0; i < freqNormalizedToPercent; i++) {
				sb.append("*");
			}
			System.out.println(sb.toString());
		}
	}
}
