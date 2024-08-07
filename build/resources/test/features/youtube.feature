#Author: edgar_duvan_l_r@hotmail.com
Feature: Validación plataforma de YouTube

  Background:
    Given que el usuario se encuentra en la pagina de Youtube con la url https://www.youtube.com/

    @Youtube-1
  Scenario Outline: Busqueda de cancion en Youtube
    When busque la cancion <nombreCancion> y la seleccione
    Then valide el nombre de la canción <nombreCancion> en la plataforma
    Examples:
    |nombreCancion|
    |Locked out of heaven|

  @Youtube-2
    Scenario: Registro en la plataforma de Youtube
      When diligencie el formulario de registro de youtube
      |nombre|apellido|fechaNacimiento|genero|correo|
      |Edgar Duvan|Lemus Ramos|20/03/1996|Masculino|loreepa23@gmail.com|
      Then podra ver el mensaje Verificar tu dirección de correo electrónico en pantalla