package tasks;

import models.UsuarioWikipedia;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import java.util.List;

import static uis.WikipediaUI.*;

public class RegistroUsuarioWikipedia implements Task {

    private List<UsuarioWikipedia> dataUsuario;

    public RegistroUsuarioWikipedia(List<UsuarioWikipedia> dataUsuario) {
        this.dataUsuario = dataUsuario;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_CREAR_CUENTA),
                Enter.theValue(dataUsuario.get(0).getUsuario()).into(TXT_USUARIO),
                Enter.theValue(dataUsuario.get(0).getCorreo()).into(TXT_CONTRASENA),
                Enter.theValue(dataUsuario.get(0).getCorreo()).into(TXT_CONFIRMAR_CONTRASENA),
                Enter.theValue(dataUsuario.get(0).getCorreo()).into(TXT_CORREO_ELECTRONICO),
                Click.on(BTN_CREAR_NUEVA_CUENTA)
        );
    }

    public static RegistroUsuarioWikipedia enPlataforma(List<UsuarioWikipedia> dataUsuario){
        return Instrumented.instanceOf(RegistroUsuarioWikipedia.class).withProperties(dataUsuario);
    }
}
