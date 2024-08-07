package tasks;

import interactions.EsperaImplicita;
import models.UsuarioYoutube;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import java.util.List;

import static uis.YoutubeUI.*;

public class RegistroUsuarioYoutube implements Task {

    private List<UsuarioYoutube> dataUsuarioYoutube;

    public RegistroUsuarioYoutube(List<UsuarioYoutube> dataUsuarioYoutube) {
        this.dataUsuarioYoutube = dataUsuarioYoutube;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(BTN_ACCEDER),
                Click.on(BTN_CREAR_CUENTA),
                Click.on(BTN_CREAR_CUENTA_USO_PERSONAL),
                Enter.theValue(dataUsuarioYoutube.get(0).getNombre()).into(TXT_NOMBRE),
                Enter.theValue(dataUsuarioYoutube.get(0).getApellido()).into(TXT_APELLIDO),
                Click.on(BTN_SIGUIENTE),
                Enter.theValue(dataUsuarioYoutube.get(0).getFechaNacimiento().split("/")[0]).into(TXT_DIA),
                SelectFromOptions.byIndex(Integer.valueOf((dataUsuarioYoutube.get(0).getFechaNacimiento().split("/")[1]).replace("0",""))).from(SELECT_MES),
                Enter.theValue(dataUsuarioYoutube.get(0).getFechaNacimiento().split("/")[2]).into(TXT_ANO),
                SelectFromOptions.byVisibleText(dataUsuarioYoutube.get(0).getGenero()).from(SELECT_GENERO),
                Click.on(BTN_SIGUIENTE),
                Enter.theValue(dataUsuarioYoutube.get(0).getCorreo()).into(TXT_CORREO_ELECTRONICO),
                Click.on(BTN_SIGUIENTE),
                EsperaImplicita.enPantallaDurante(30));
    }

    public static RegistroUsuarioYoutube enPlataforma(List<UsuarioYoutube> dataUsuarioYoutube){
        return Instrumented.instanceOf(RegistroUsuarioYoutube.class).withProperties(dataUsuarioYoutube);
    }
}
