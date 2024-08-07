package uis;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class YoutubeUI {
    public static final Target TXT_BUSQUEDA = Target.the("").located(By.xpath("//input[@id='search']"));
    public static final Target BTN_CANCION = Target.the("").locatedBy("//yt-formatted-string[contains(text(),'{0}')]//ancestor::a");
    public static final Target TXT_VALIDACION_TITULO_CANCION = Target.the("").locatedBy("//h1[contains(@class,'metadata')]//yt-formatted-string[contains(text(),'{0}')]");
    public static final Target BTN_ACCEDER = Target.the("").located(By.xpath("//a[@aria-label='Acceder']"));
    public static final Target BTN_CREAR_CUENTA = Target.the("").located(By.xpath("//span[text()='Crear cuenta']"));
    public static final Target BTN_CREAR_CUENTA_USO_PERSONAL = Target.the("").located(By.xpath("//span[text()='Para uso personal']//ancestor::li"));
    public static final Target TXT_NOMBRE = Target.the("").located(By.xpath("//input[@id='firstName']"));
    public static final Target TXT_APELLIDO = Target.the("").located(By.xpath("//input[@id='lastName']"));
    public static final Target BTN_SIGUIENTE = Target.the("").located(By.xpath("//span[text()='Siguiente']//ancestor::button"));
    public static final Target TXT_DIA = Target.the("").located(By.xpath("//input[@id='day']"));
    public static final Target SELECT_MES = Target.the("").located(By.xpath("//select[@id='month']"));
    public static final Target TXT_ANO = Target.the("").located(By.xpath("//input[@id='year']"));
    public static final Target SELECT_GENERO = Target.the("").located(By.xpath("//select[@id='gender']"));
    public static final Target TXT_CORREO_ELECTRONICO = Target.the("").located(By.xpath("//input[@id='identifierId']"));
    public static final Target TXT_VALIDACION_REGISTRO_USUARIO = Target.the("").locatedBy("//span[text()='{0}']");
}
