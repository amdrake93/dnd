package Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Dice.Die.Flags;

public class DieCommandProcessor {
	private static final String BIG_UNDERLINE = "----------";

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				String input = sc.nextLine();
				String[] inputSplitOnSpaces = input.split(" ");

				if (inputSplitOnSpaces.length < 1) {
					System.out.println("No input found\n");
					continue;
				}

				Flags flag = Die.Flags.NO;
				if (inputSplitOnSpaces.length > 1) {
					flag = Flags.valueOf(inputSplitOnSpaces[1].toUpperCase());
				}

				String dieInput = inputSplitOnSpaces[0];
				List<String> additions = new ArrayList<>(Arrays.asList(dieInput.split("\\+")));
				List<String> purgedAdditions = new ArrayList<>();
				List<String> subtractions = findSubtractionsFromAdditionSplit(additions, purgedAdditions);

				System.out.println("Additions");
				Long additionResult = handleDieRolls(purgedAdditions, flag);
				System.out.println(BIG_UNDERLINE);
				System.out.println("Subtractions");
				Long subtractionResult = handleDieRolls(subtractions, flag);
				System.out.println(BIG_UNDERLINE);
				System.out.println("Result: " + (additionResult - subtractionResult));
			} catch (Throwable e) {
				System.out.println("error");
			}
		}
	}

	private static Long handleDieRolls(List<String> dice, Flags flag) {
		Long result = 0l;
		for (String dieString : dice) {
			if (dieString.isEmpty()) {
				continue;
			}
			if (dieString.indexOf("d") == (dieString.length() - 1)) {
				System.out.println("Missing a die type\n");
				continue;
			}
			String[] diceParts = dieString.split("d");
			if (diceParts.length == 1) {
				int modifier = Integer.valueOf(diceParts[0]);
				result += modifier;
				continue;
			}
			Integer amount = diceParts[0].isEmpty() ? 1 : Integer.valueOf(diceParts[0]);
			Integer value = Integer.valueOf(diceParts[1]);

			Die die = new Die(amount, value);
			int dieResult = die.rollDie(flag);
			result += dieResult;
		}
		return result;
	}

	private static List<String> findSubtractionsFromAdditionSplit(List<String> additions, List<String> purgedAdditions) {
		List<String> subtractions = new ArrayList<>();
		for (String addition : additions) {
			String[] possibleSubtractions = addition.split("\\-");
			if (possibleSubtractions.length > 1) {
				purgedAdditions.add(possibleSubtractions[0]);
				for (int i = 1; i < possibleSubtractions.length; i++) {
					subtractions.add(possibleSubtractions[i]);
				}
			} else {
				purgedAdditions.add(possibleSubtractions[0]);
			}
		}
		return subtractions;
	}
}
