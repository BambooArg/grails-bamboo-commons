package ar.com.bamboo.commons

import ar.com.bamboo.commons.number.CurrencyUtils

class CurrencyTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "bambooCurrency"

    /**
     * Imprime la hora en formato humano.
     * Pasado un string del tipo HHMM devuelve HH:MM
     *
     * @attr price
     * */
    def formatPrice = {attrs ->
        String output = ""
        if (attrs.price == null){
            output = "---"
        }else{
            output = CurrencyUtils.formatArgentinianPrice(attrs.price)
        }
        out << output
    }
}
