package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import uis.YoutubeUI;
import utils.utilities.JavaUtilities;

public class BusquedadeCanción implements Task {

    private String cancion;

    public BusquedadeCanción(String cancion) {
        this.cancion = cancion;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(cancion).into(YoutubeUI.TXT_BUSQUEDA).thenHit(Keys.ENTER),
                Click.on(YoutubeUI.BTN_CANCION.of(JavaUtilities.capitalizeWords(cancion))),
                WaitUntil.the(YoutubeUI.TXT_VALIDACION_TITULO_CANCION.of(JavaUtilities.capitalizeWords(cancion)), WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds());
    }

    public static BusquedadeCanción enYoutube(String cancion){
        return Instrumented.instanceOf(BusquedadeCanción.class).withProperties(cancion);
    }
}
