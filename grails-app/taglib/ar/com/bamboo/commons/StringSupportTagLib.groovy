package ar.com.bamboo.commons

class StringSupportTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "stringSupport"

    /**
     * Imprime el string con ellipsis con el máximo pasado en maxLength
     * @attr maxLength
     * */
    def ellipsis = {attrs, body ->
        StringBuilder output = new StringBuilder("")
        if (attrs.maxLength){
            Integer maxLength = Integer.parseInt(attrs.maxLength)
            Integer bodyLength = body().size()
            if (maxLength >= bodyLength){
                output.append(body())
            }else{
                output.append(body().substring(0, maxLength)).append("...")
            }
        }else{
            throw new IllegalArgumentException("The parameter max length is required")
        }
        out << output.toString()
    }
}
