package uis;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class WikipediaUI {
    public static final Target TXT_VALIDACION_BIENVENIDA = Target.the("").locatedBy("//h1[contains(@id,'{0}')]");
    public static final Target TXT_BUSQUEDA = Target.the("").located(By.xpath("//input[@placeholder='Buscar en Wikipedia']"));
    public static final Target BTN_BUSQUEDA = Target.the("").located(By.xpath("//button[text()='Buscar']"));
    public static final Target TXT_VALIDACION_ANALISIS = Target.the("").locatedBy("//h3[contains(text(),'{0}')]");
    public static final Target BTN_CREAR_CUENTA = Target.the("").located(By.xpath("//div[contains(@id,'overflow')]//span[contains(text(),'Crear')]//ancestor::a"));
    public static final Target TXT_USUARIO = Target.the("").located(By.xpath("//input[contains(@placeholder,'nombre')]"));
    public static final Target TXT_CONTRASENA = Target.the("").located(By.xpath("//input[contains(@placeholder,'una')]"));
    public static final Target TXT_CONFIRMAR_CONTRASENA = Target.the("").located(By.xpath("//input[contains(@placeholder,'nuevo')]"));
    public static final Target TXT_CORREO_ELECTRONICO = Target.the("").located(By.xpath("//input[contains(@placeholder,'correo')]"));
    public static final Target BTN_CREAR_NUEVA_CUENTA = Target.the("").located(By.xpath("//button[contains(text(),'Crea')]"));
}
