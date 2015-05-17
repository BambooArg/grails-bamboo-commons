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

Para publicar un release se debe ejecutar

```grails
grails publish-plugin

```

Para publicar un snapshot se debe ejecutar

```grails
grails publish-plugin --repository=bambooRepoSnapshot

```

El repositorio default para la publicación es http://nexus-bambooarg.rhcloud.com/nexus/content/groups/public/


###**Atención**
Tener en cuenta que se tiene que tener configurado en .grails/setting.groovy
```groovy
grails.project.repos.default = "bambooRepo"
grails.project.repos.bambooRepo.url = "http://nexus-bambooarg.rhcloud.com/nexus/content/repositories/releases/"
grails.project.repos.bambooRepo.type = "maven"
grails.project.repos.bambooRepo.username = username (poner el username real)
grails.project.repos.bambooRepo.password = password (poner el password real)

grails.project.repos.bambooRepoSnapshot.url = "http://nexus-bambooarg.rhcloud.com/nexus/content/repositories/snapshots/"
grails.project.repos.bambooRepoSnapshot.type = "maven"
grails.project.repos.bambooRepoSnapshot.username = username
grails.project.repos.bambooRepoSnapshot.password = password

```


#Test

El proyecto usa travis-ci como entorno de integración continua. https://travis-ci.org/orkonano/grails-bamboo-commons.
Se ejecutan tantos los test unitarios como integrales, corriendo la base de datos de test en memoria.

