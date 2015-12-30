[![Build Status](https://api.travis-ci.org/BambooArg/grails-bamboo-commons.svg?branch=master)](https://api.travis-ci.org/BambooArg/grails-bamboo-commons.svg?branch=master)

grails-bamboo-commons
=====================

El plugin está destinado a manejar todo lo relativo a las cuestiones comunes de los proyectos de Bamboo


#Instalación

Agregar el plugin al proyecto
```groovy
compile ":grails-bamboo-commons:0.1.0"
```

#Build

Para compilar el proyecto e intalarlo localmente se debe ejecutar

 ```script
gradle install
```

Para publicar un release se debe ejecutar

```script
gradle publishMavenPublicationToBambooReleaseRepository

```

Para publicar un snapshot se debe ejecutar

```script
gradle publishMavenPublicationToBambooSNAPSHOTRepository

```

El repositorio default para la publicación es http://nexus-bambooarg.rhcloud.com/nexus/content/groups/public/


###**Atención**
Tener en cuenta que se tiene que tener configurado las variables de entorno para poder publicar
```script
BAMBOO_REPOSITORY_USERNAME
BAMBOO_REPOSITORY_PASSWORD

```

o las propiedades del proyecto
```script
bambooRepositoryUsername
bambooRepositoryPassword

```

#Test

El proyecto usa travis-ci como entorno de integración continua. https://travis-ci.org/BambooArg/grails-bamboo-commons.
Se ejecutan tantos los test unitarios como integrales, corriendo la base de datos de test en memoria.

