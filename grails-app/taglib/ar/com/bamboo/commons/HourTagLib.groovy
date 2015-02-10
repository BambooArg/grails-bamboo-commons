package ar.com.bamboo.commons

import ar.com.bamboo.commons.hour.HourUtils

class HourTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "bambooHour"


    /**
     * Imprime la hora en formato humano.
     * Pasado un string del tipo HHMM devuelve HH:MM
     *
     * @attr hour es requerido
     * */
    def humanFormat = {attrs ->
        assert attrs.hour
        out << HourUtils.hourToHumanFormat(attrs.hour)
    }
}
