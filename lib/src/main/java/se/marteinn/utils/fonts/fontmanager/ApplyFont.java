package se.marteinn.utils.fonts.fontmanager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Bind font to view. Will be applied with FontsManager.apply are run.
 *
 * Created by martinsandstrom on 2014-06-21.
 */
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplyFont {
    String value();
}

