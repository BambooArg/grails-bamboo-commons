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

 ```grails
grails maven-install
```

Para publicarlo se deje ejecutar

```grails
grails publish-plugin --protocol=webdav
```

El repositorio default para la publicación es https://repository-orkoapp.forge.cloudbees.com/snapshot/


###**Atención**
Tener en cuenta que se tiene que tener configurado en .grails/setting.groovy
```groovy
grails.project.repos.cloudbees.url = "dav:https://repository-orkoapp.forge.cloudbees.com/snapshot/"
grails.project.repos.cloudbees.username = yourUsername
grails.project.repos.cloudbees.password = yourPass
```


#Test

El proyecto usa travis-ci como entorno de integración continua. https://travis-ci.org/orkonano/grails-bamboo-commons.
Se ejecutan tantos los test unitarios como integrales, corriendo la base de datos de test en memoria.

