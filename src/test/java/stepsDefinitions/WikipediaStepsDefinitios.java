package stepsDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import models.UsuarioWikipedia;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import questions.ValidarPopUp;
import tasks.BusquedaPalabra;
import tasks.RegistroUsuarioWikipedia;
import uis.WikipediaUI;
import utils.utilities.SelfHealingTest;

import java.io.IOException;
import java.util.List;

public class WikipediaStepsDefinitios {

    @Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que el usuario se encuentra en la pagina de Wikipedia con la url (.*)$")
    public void queElUsuarioSeEncuentraEnLaPaginaDeWikipediaConLaUrlHttpsEsWikipediaOrgWikiWikipediaPortada(String url) {
        OnStage.theActorCalled("").can(BrowseTheWeb.with(SelfHealingTest.withChromeOptions(url)));
    }

    @Then("^podra validar el titulo (.*) en la portada de Wikipedia$")
    public void podraValidarElTituloBienvenidosAWikipediaEnLaPortadaDeWikipedia(String mensaje) {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(WikipediaUI.TXT_VALIDACION_BIENVENIDA.of(mensaje.replace(" ","_"))), WebElementStateMatchers.isVisible()));
    }

    @When("^busque la palabra (.*)$")
    public void busqueLaPalabraLockedOutOfHeaven(String palabra) {
        OnStage.theActorInTheSpotlight().attemptsTo(BusquedaPalabra.enWikipedia(palabra));
    }

    @Then("^valide el titulo (.*) en la plataforma$")
    public void valideElTituloAnálisisCEEMEnLaPlataforma(String titulo) {
        OnStage.theActorInTheSpotlight().attemptsTo(Scroll.to(WikipediaUI.TXT_VALIDACION_ANALISIS.of(titulo)).andAlignToTop());
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(WikipediaUI.TXT_VALIDACION_ANALISIS.of(titulo)), WebElementStateMatchers.containsText(titulo)));
    }

    @When("^diligencie el formulario de registro de wikipedia$")
    public void diligencieElFormularioDeRegistroDeWikipedia(List<UsuarioWikipedia> dataUsuario) {
        OnStage.theActorInTheSpotlight().attemptsTo(RegistroUsuarioWikipedia.enPlataforma(dataUsuario));
    }

    @Then("^podria ver el mensaje de catpcha Verificar tu dirección de correo electrónico en pantalla$")
    public void podriaVerElMensajeDeCatpchaVerificarTuDirecciónDeCorreoElectrónicoEnPantalla() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarPopUp.enPantalla()));
    }
}
