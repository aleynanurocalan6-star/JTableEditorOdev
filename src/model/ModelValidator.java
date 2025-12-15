package model;

import java.lang.reflect.Field;

public class ModelValidator {

	public static boolean isValid(Object model) {

		if (model == null)
			return false;

		try {
			for (Field field : model.getClass().getDeclaredFields()) {

				field.setAccessible(true);
				Object value = field.get(model);

				// null kontrolü
				if (value == null)
					return false;

				// String boşluk kontrolü
				if (value instanceof String && ((String) value).trim().isEmpty())
					return false;
			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
