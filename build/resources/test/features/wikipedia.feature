#Author: edgar_duvan_l_r@hotmail.com
Feature: Validación plataforma de Wikipedia

  Background:
    Given que el usuario se encuentra en la pagina de Wikipedia con la url https://es.wikipedia.org/wiki/Wikipedia:Portada

    @Wikipedia-1
    Scenario Outline: Validación del Título de la Portada de Wikipedia
      Then podra validar el titulo <titulo> en la portada de Wikipedia
      Examples:
      |titulo|
      |Bienvenidos a Wikipedia|

      @Wikipedia-2
  Scenario Outline: Busqueda de palabra en Wikipedia
    When busque la palabra <palabra>
    Then valide el titulo <titulo> en la plataforma
    Examples:
      |palabra|titulo|
      |Sistema|Análisis CEEM|

  @Wikipedia-3
    Scenario: Registro en la plataforma de Wikipedia
      When diligencie el formulario de registro de wikipedia
        |usuario|contrasena|correo|
        |EdgarLemus|innovacion2024*|edgar_duvan_l_r@hotmail.com|
      Then podria ver el mensaje de catpcha Verificar tu dirección de correo electrónico en pantalla