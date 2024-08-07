# Automatización_Konex_Innovation

Realiza la automatización en las paginas [Youtube](https://www.youtube.com/) y [Wikipedia](https://es.wikipedia.org/wiki/Wikipedia:Portada) usando las herramientas [Gradle](https://gradle.org/), [Java](https://www.java.com/es/), [SerenityBDD](https://serenity-bdd.github.io/theserenitybook/latest/index.html), [Cucumber](https://cucumber.io/) y Screenplay.

## Estructura Codigo Fuente

La estructura del codigo fue realizada con Screenplay de la siguiente forma:
<table>
<tr>
  <th>Tasks</th>
  <td>
    <h6>Contiene todas las tareas que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Interactions</th>
  <td>
    <h6>Contiene todas las interaciones que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Models</th>
  <td>
    <h6>Contiene todos los modelos que se utilizaran para la construccion de la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Uis</th>
  <td>
    <h6>Contiene todos los elementos de la interface usuario mapeados en la pagina</h6>
  </td>
</tr>
  <tr>
  <th>DriversConfig</th>
  <td>
    <h6>Contiene todos los drivers de los navegadores que se descargan automaticamente</h6>
  </td>
</tr>
  <tr>
  <th>Runners</th>
  <td>
    <h6>Contiene todos los ejecutores de las pruebas automatizadas</h6>
  </td>
</tr>
  <tr>
  <th>Steps Definitions</th>
  <td>
    <h6>Contiene todos los pasos de la ejecucion de cada prueba automatizada</h6>
  </td>
</tr>
  <tr>
  <th>Features</th>
  <td>
    <h6>Contiene todos los esenarios codificados en lenguaje Gherking</h6>
  </td>
</tr>
<tr>
  <th>Questions</th>
  <td>
    <h6>Contiene todos los esenarios codificados en lenguaje Gherking</h6>
  </td>
</tr>
</table>

#### Tasks

##### BusquedadeCanción

Este código implementa una tarea automatizada que busca una canción en YouTube, hace clic en el resultado relevante, y espera a que el título de la canción aparezca en la página. Utiliza el patrón Screenplay de Serenity BDD, lo que facilita la lectura y mantenimiento de scripts de prueba al describir las interacciones en términos de lo que hace el Actor.

```java
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
```
#### BusquedaPalabra

Este código define una tarea de automatización que permite a un Actor buscar una palabra en Wikipedia. Utiliza el patrón Screenplay de Serenity para estructurar los pasos de manera clara y reutilizable.

```java
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
```

#### RegistroUsuarioWikipedia

Este código automatiza el proceso de registro de un usuario en Wikipedia, estructurado mediante el patrón Screenplay de Serenity BDD. Utiliza una lista de objetos UsuarioWikipedia para extraer datos de registro y lleva a cabo las acciones necesarias mediante un Actor.

```java
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
```

#### RegistroUsuarioYoutube

Este código automatiza el proceso de registro de un usuario en YouTube. Utiliza el patrón Screenplay de Serenity BDD para estructurar los pasos de manera clara y reutilizable. La información del usuario se extrae de una lista de objetos UsuarioYoutube, lo que permite flexibilidad en las pruebas y escenarios.

```java
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
```

### Interactions

#### EsperaImplicita

Realiza la espera implicita, esta tarea implementa la interfaz Interaction y sobreescribe su metodo, tambien recibe un parametro de tipo int.

```java
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
```

### Models

#### UsuarioWikipedia

La clase UsuarioWikipedia es un modelo de datos sencillo que encapsula la información necesaria para representar a un usuario en Wikipedia. Incluye atributos para el nombre de usuario, contraseña y correo electrónico, y proporciona métodos getter y setter para acceder y modificar estos atributos. Este modelo es útil en pruebas automatizadas o cualquier aplicación que requiera manipular datos de usuario para interacciones con Wikipedia.

```java
public class UsuarioWikipedia {

    private String usuario,contrasena,correo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
```

#### UsuarioYoutube

La clase UsuarioYoutube es un modelo de datos que representa a un usuario de YouTube con atributos para su nombre, apellido, fecha de nacimiento, género y correo electrónico. Los métodos getter y setter proporcionan una interfaz para acceder y modificar estos atributos, lo que es útil para gestionar datos de usuario en aplicaciones o pruebas automatizadas.

```java
public class UsuarioYoutube {

    private String nombre,apellido,fechaNacimiento,genero,correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
```

### Uis

#### WikipediaUI

La clase WikipediaUI utiliza el patrón Screenplay para definir un conjunto de localizadores que permiten a los actores de prueba interactuar con la interfaz de usuario de Wikipedia. Cada Target representa un elemento de la página web que puede ser utilizado en pruebas automatizadas, facilitando el acceso a campos de entrada, botones y elementos de validación mediante XPath. Esto permite que las pruebas sean más legibles y fáciles de mantener.

```java
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
```

#### YoutubeUI

La clase YoutubeUI utiliza el patrón Screenplay para definir un conjunto de localizadores que permiten a los actores de prueba interactuar con la interfaz de usuario de YouTube. Cada Target representa un elemento de la página web que puede ser utilizado en pruebas automatizadas, facilitando el acceso a campos de entrada, botones y elementos de validación mediante XPath. Esto ayuda a estructurar las pruebas de manera más legible y mantenible.

```java
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
```

### driverConfig

#### DriverFactory

La clase DriverFactory ofrece una forma centralizada de crear instancias de WebDriver para diferentes navegadores. Utiliza WebDriverManager para manejar automáticamente la configuración de los controladores, simplificando el proceso de ejecución de pruebas en múltiples navegadores y asegurando que los controladores estén actualizados. El método getNewInstance permite ejecutar navegadores en modo normal o sin cabeza, según se requiera para las pruebas automatizadas.

```java
public class DriverFactory {

    public static WebDriver getNewInstance(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome-headless":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("start-maximized");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "firefox-headless":
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                firefoxBinary.addCommandLineOptions("--window-size=1280x720");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                WebDriverManager.chromedriver().clearDriverCache().setup();
                WebDriverManager.chromedriver().clearResolutionCache().setup();
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}
```

#### DriverHolder

La clase DriverHolder proporciona un almacenamiento seguro para instancias de WebDriver en un contexto de pruebas concurrentes. Utiliza ThreadLocal para asegurar que cada hilo de prueba tenga su propia instancia de WebDriver, evitando interferencias entre pruebas que se ejecutan en paralelo. Los métodos getDriver y setDriver permiten acceder y modificar la instancia de WebDriver específica para cada hilo, facilitando la gestión de pruebas automatizadas en un entorno multihilo.

```java
public class DriverHolder {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverHolder.driver.set(driver);
    }
}
```

### Questions

#### ValidarPopUp

La clase ValidarPopUp utiliza SikuliX para detectar la presencia de un pop-up en la pantalla comparando una imagen proporcionada con lo que se muestra en la pantalla en tiempo real. Implementa la interfaz Question de Serenity BDD para integrarse en el flujo de pruebas automatizadas, permitiendo validar visualmente la presencia de elementos gráficos. El método answeredBy realiza la búsqueda del pop-up y el método estático enPantalla proporciona una forma conveniente de instanciar la clase para las pruebas.

```java
package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ValidarPopUp implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        Path projectPath = Paths.get("").toAbsolutePath();
        Path relativePath = projectPath.resolve("src/main/resources/image/popup.PNG");
        File file = relativePath.toFile();

        Screen s = new Screen();
        Pattern fileInputTextBox = new Pattern(file.getAbsolutePath());

        if(s.exists(fileInputTextBox) != null){
            return true;
        }else{
            return false;
        }
    }

    public static ValidarPopUp enPantalla(){
        return new ValidarPopUp();
    }
}

```

### utils

#### JavaUtilities

La clase JavaUtilities contiene un método capitalizeWords que formatea una cadena de texto para que cada palabra comience con una letra mayúscula, mientras que las demás letras de cada palabra se convierten a minúsculas. Este método maneja textos nulos o vacíos de manera segura y usa un StringBuilder para construir eficientemente la cadena resultante.

```java
public class JavaUtilities {

    public static String capitalizeWords(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)));
                result.append(word.substring(1).toLowerCase());
                result.append(" ");
            }
        }

        return result.toString().trim();
    }
}
```

#### SelfHealingTest

La clase SelfHealingTest proporciona métodos para configurar un SelfHealingDriver para realizar pruebas automáticas con los navegadores Chrome y Edge. Utiliza ChromeOptions para configurar opciones específicas para Chrome y DriverFactory para obtener instancias de WebDriver. El SelfHealingDriver ayuda a gestionar elementos dinámicos en la interfaz de usuario, haciendo que las pruebas sean más robustas y menos propensas a fallar debido a cambios en la UI. La clase también utiliza TestNG Listeners para manejar eventos de prueba.

```java
@Listeners(TestExecutionListener.class)
public class SelfHealingTest {

    public static WebDriver driver;

    public static SelfHealingDriver withChromeOptions(String url) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--allow-running-insecure-content --disable-popup-blocking --disable-dev-shm-usage");
        options.addArguments("--disable-infobars --test-type --disable-extensions --disable-translate");
        options.addArguments("--ignore-certificate-errors --incognito --no-sandbox --disable-download-notification");
        options.addArguments("use-fake-ui-for-media-stream");

        WebDriver delegate = DriverFactory.getNewInstance("chrome");
        setDriver(delegate);
        driver = SelfHealingDriver.create(delegate);
        driver.manage().window().maximize();
        driver.navigate().to(url);

        return SelfHealingDriver.create(delegate);
    }

    public static SelfHealingDriver withEdge(String url) {
        WebDriver delegate = DriverFactory.getNewInstance("edge");
        setDriver(delegate);
        driver = SelfHealingDriver.create(delegate);
        driver.manage().window().maximize();
        driver.navigate().to(url);

        return SelfHealingDriver.create(delegate);
    }

}
```

#### TestExecutionListener

La clase TestExecutionListener es un ITestListener que se utiliza para mejorar los reportes de pruebas en TestNG añadiendo capturas de pantalla cuando una prueba falla. Utiliza la anotación @Attachment de Allure para adjuntar la captura de pantalla al reporte de prueba. Implementa los métodos de ITestListener, aunque solo el método onTestFailure está configurado para capturar y guardar una captura de pantalla. Los otros métodos están vacíos y no realizan acciones adicionales.

```java
public class TestExecutionListener implements ITestListener {

    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot(getDriver());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
```

### Runners

#### WikipediaRunner

La clase WikipediaRunner es el punto de entrada para ejecutar pruebas automatizadas usando Cucumber y Serenity BDD. Está configurada para buscar las definiciones de pasos en el paquete stepsDefinitions y para ejecutar el archivo de características ubicado en src/test/resources/features/wikipedia.feature. Utiliza CucumberWithSerenity como corredor de pruebas para integrar Cucumber con Serenity y generar reportes detallados. El formato de los métodos generados para los pasos de Cucumber es camelCase.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(glue = "stepsDefinitions",
        features = "src/test/resources/features/wikipedia.feature",
        snippets = SnippetType.CAMELCASE)
public class WikipediaRunner {
}
```

#### YoutubeRunner

La clase YoutubeRunner es el punto de entrada para ejecutar pruebas automatizadas utilizando Cucumber y Serenity BDD. Está configurada para buscar las definiciones de pasos en el paquete stepsDefinitions y para ejecutar el archivo de características en src/test/resources/features/youtube.feature. Utiliza CucumberWithSerenity como corredor de pruebas para proporcionar reportes detallados y utiliza camelCase para los nombres de métodos generados para los pasos.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(glue = "stepsDefinitions",
        features = "src/test/resources/features/youtube.feature",
        snippets = SnippetType.CAMELCASE)
public class YoutubeRunner {
}
```


### StepsDefinitions

#### WikipediaStepsDefinitios

La clase WikipediaStepsDefinitions define los pasos para las pruebas automatizadas de Wikipedia utilizando Cucumber y Serenity BDD. Establece el entorno de prueba, define cómo interactuar con la página de Wikipedia, y valida varios aspectos de la interfaz de usuario como la visibilidad de títulos y mensajes de captcha. Utiliza actores para realizar acciones y verificar resultados, y emplea tareas específicas para simular la interacción del usuario con la aplicación.

```java
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
```

### YoutubeStepsDefinitions

La clase YoutubeStepsDefinitions define los pasos para las pruebas automatizadas de YouTube usando Cucumber y Serenity BDD. Establece el entorno de prueba, define cómo interactuar con la página de YouTube, y valida aspectos como la presencia de títulos de canciones y mensajes en la interfaz de usuario. Utiliza tareas específicas para simular la interacción del usuario con la aplicación y emplea utilidades para formatear texto y verificar mensajes.

```java
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
```

### Features

#### wikipedia.feature

Estos escenarios están diseñados para verificar la funcionalidad de la página de Wikipedia, la búsqueda de palabras, y el proceso de registro.

```cucumber
#Author: edgar_duvan_l_r@hotmail.com
Feature: Validación plataforma de Wikipedia

  Background:
    Given que el usuario se encuentra en la pagina de Wikipedia con la url https://es.wikipedia.org/wiki/Wikipedia:Portada

    Scenario Outline: Validación del Título de la Portada de Wikipedia
      Then podra validar el titulo <titulo> en la portada de Wikipedia
      Examples:
      |titulo|
      |Bienvenidos a Wikipedia|

  Scenario Outline: Busqueda de palabra en Wikipedia
    When busque la palabra <palabra>
    Then valide el titulo <titulo> en la plataforma
    Examples:
      |palabra|titulo|
      |Sistema|Análisis CEEM|

    @Manual
    Scenario: Registro en la plataforma de Wikipedia
      When diligencie el formulario de registro de wikipedia
        |usuario|contrasena|correo|
        |EdgarLemus|innovacion2024*|edgar_duvan_l_r@hotmail.com|
      Then podria ver el mensaje de catpcha Verificar tu dirección de correo electrónico en pantalla
```

#### youtube.feature

Estos escenarios están diseñados para verificar la búsqueda de canciones en YouTube y el proceso de registro en la plataforma.

```cucumber
#Author: edgar_duvan_l_r@hotmail.com
Feature: Validación plataforma de YouTube

  Background:
    Given que el usuario se encuentra en la pagina de Youtube con la url https://www.youtube.com/

    @Manual
  Scenario Outline: Busqueda de cancion en Youtube
    When busque la cancion <nombreCancion> y la seleccione
    Then valide el nombre de la canción <nombreCancion> en la plataforma
    Examples:
    |nombreCancion|
    |Locked out of heaven|

    Scenario: Registro en la plataforma de Youtube
      When diligencie el formulario de registro de youtube
      |nombre|apellido|fechaNacimiento|genero|correo|
      |Edgar Duvan|Lemus Ramos|20/03/1996|Masculino|loreepa23@gmail.com|
      Then podra ver el mensaje Verificar tu dirección de correo electrónico en pantalla
```

## Ejecucion

Al momento de ejecutar el proyecto y obtener el reporte debemos ubicarnos en la carpeta del proyecto y abrir el `CMD` para ejecutar los siguientes comandos:

Este comando ejecutara todos los escenarios implementados en el proyecto:
```yml
    gradle clean test aggregate
```



```cmd
    7 actionable tasks: 7 executed
```

Al finalizar debemos ingresar y abrir el archivo `index.html` que se encuentra en la siguiente ruta

```yml
  <ProyectoName>\target\site\serenity\index.html
```