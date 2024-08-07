package interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class EsperaImplicita implements Interaction {

    private int segundos;
    private WebDriver driver;

    public EsperaImplicita(int segundos) {
        this.segundos = segundos;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        driver = BrowseTheWeb.as(actor).getDriver();
        driver.manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
    }

    public static Performable enPantallaDurante(int segundos){
        return Instrumented.instanceOf(EsperaImplicita.class).withProperties(segundos);
    }
}
