# Api Mutant

Esta API detecta si un humano es Mutante mediante una cadena de ADN.

## Entorno de Desarrollo

- Systema Operativo: macOS Big Sur
- IDE: IntelliJ IDEA
- Version de Open JDK(Java): 11.0.12
- Framework: Spring
- Gestor de Dependecias: Gradle 7.2

### Instalacion

Clonar el proyecto el IDE de preferencia y hacer el build con el comando ./gradlew build

### Cosumo del Api

El api se encuentra alojado en AWS y tiene los siguientes:

- **POST**: '/mutant' analiza la cadena enviada y devuelve un **true** si es mutante con un **status** *200* de lo
  contrario devuelve **false** con **status** *
  403*  [API Mutant](http://xmen-env.eba-ztfabppj.us-east-2.elasticbeanstalk.com/mutant)
- **GET**: Devuelve el numero de mutantes y el numero de humanos analizados por
  el [API Stats](http://xmen-env.eba-ztfabppj.us-east-2.elasticbeanstalk.com/stats)

El consumo se puede hacer de la siguiente manera:

###### macOs,Linux, Windows

Abrir una terminal y ejecutar los siguientes comandos:

- curl --location --request POST 'http://xmen-env.eba-ztfabppj.us-east-2.elasticbeanstalk.com/mutant' --header '
  Content-Type: application/json' --data-raw '{"dna": ["ATGCGA","CAGTTC","TTATGT","AGATGG","CCTCTA","TCACTG"]}'
- curl --location --request GET 'http://xmen-env.eba-ztfabppj.us-east-2.elasticbeanstalk.com/stats'

En algunos sistemas operativos es posible que no se encuentre la libreria **curl** si es asi se puede instalar o de lo
contrario instalar uno de los siguiente Sofrware:

- [Postman](https://www.postman.com/downloads/)
- [Soap UI](https://www.soapui.org/downloads/soapui/)

Cada uno de estos software tiene tutoriales para consumir un api.

- [Ducumentacion Postman](https://learning.postman.com/docs/getting-started/introduction/)
- [Documentacion Soap UI](https://www.soapui.org/getting-started/rest-testing/)

## Autor

###### Jackson Asprilla Blandon
