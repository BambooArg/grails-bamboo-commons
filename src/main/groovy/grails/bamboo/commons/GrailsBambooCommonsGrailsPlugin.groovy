package grails.bamboo.commons

import grails.plugins.Plugin

class GrailsBambooCommonsGrailsPlugin extends Plugin{
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.x > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Bamboo Commons Plugin" // Headline display name of the plugin
    def author = "Mariano Kfuri"
    def authorEmail = "marianoekfuri@gmail.com"
    def description = '''\
Proyecto commons para bamboo, donde se encuentran operatorias comunes que puedan servir a todos
los proyectos
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/BambooArg/grails-bamboo-common"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Bamboo" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Mariano Kfuri", email: "marianoekfuri@gmail.com" ],
                       [ name: "Maximialiano Micciullo", email: "mmicciullo@gmail.com" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "Github", url: "https://github.com/BambooArg/grails-bamboo-commons/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/BambooArg/grails-bamboo-commons" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    Closure doWithSpring() {{->

    }}

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
