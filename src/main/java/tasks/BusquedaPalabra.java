package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static uis.WikipediaUI.*;

public class BusquedaPalabra implements Task {

    private String palabra;

    public BusquedaPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(TXT_BUSQUEDA, WebElementStateMatchers.isClickable()).forNoMoreThan(30).seconds(),
                Enter.keyValues(palabra).into(TXT_BUSQUEDA),
                Click.on(BTN_BUSQUEDA));
    }

    public static BusquedaPalabra enWikipedia(String palabra){
        return Instrumented.instanceOf(BusquedaPalabra.class).withProperties(palabra);
    }
}
