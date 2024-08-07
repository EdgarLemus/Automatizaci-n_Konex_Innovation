package stepsDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import models.UsuarioYoutube;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import tasks.BusquedadeCanción;
import tasks.RegistroUsuarioYoutube;
import uis.YoutubeUI;
import utils.utilities.JavaUtilities;
import utils.utilities.SelfHealingTest;

import java.io.IOException;
import java.util.List;

public class YoutubeStepsDefinitions {

    @Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que el usuario se encuentra en la pagina de Youtube con la url (.*)$")
    public void queElUsuarioSeEncuentraEnLaPaginaDeYoutubeConLaUrlHttpsWwwYoutubeCom(String url) {
        OnStage.theActorCalled("").can(BrowseTheWeb.with(SelfHealingTest.withChromeOptions(url)));
    }

    @When("^busque la cancion (.*) y la seleccione$")
    public void busqueLaCancionLockedOutOfHeavenYLaSeleccione(String cancion) {
        OnStage.theActorInTheSpotlight().attemptsTo(BusquedadeCanción.enYoutube(cancion));
    }

    @Then("^valide el nombre de la canción (.*) en la plataforma$")
    public void valideElNombreDeLaCanciónLockedOutOfHeavenEnLaPlataforma(String cancion) {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(YoutubeUI.TXT_VALIDACION_TITULO_CANCION.of(JavaUtilities.capitalizeWords(cancion))), WebElementStateMatchers.containsText(JavaUtilities.capitalizeWords(cancion))));
    }

    @When("^diligencie el formulario de registro de youtube$")
    public void diligencieElFormularioDeRegistroDeYoutube(List<UsuarioYoutube> dataUsuarioYoutubeYoutube) {
        OnStage.theActorInTheSpotlight().attemptsTo(RegistroUsuarioYoutube.enPlataforma(dataUsuarioYoutubeYoutube));
    }

    @Then("^podra ver el mensaje (.*) en pantalla$")
    public void podraVerElMensajeVerificarTuDirecciónDeCorreoElectrónicoEnPantalla(String mensaje) {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(YoutubeUI.TXT_VALIDACION_REGISTRO_USUARIO.of(mensaje)), WebElementStateMatchers.isVisible()));
    }
}
