package ar.com.bamboo.commons.number
/**
 * Created by orko on 29/09/14.
 */
class NumberUtils {

    /**
     * Determina si la recta formada por los puntos (x0, x1) está interceptada por la recta (y0, y1)
     * El método arroja IllegalArgumentException si algún parámetro es null o si la recta (x0, x1) o (y0, y1) está mal
     * formada o no es una recta. Ej x0 > x1
     * @param x0
     * @param x1
     * @param y0
     * @param y1
     * @return true si la recta (y0, y1) intercepta a la recta (x0, x1)
     */
    public static boolean intersectionLine(Number x0, Number x1, Number y0, Number y1){
        if (x0 == null || x1 == null || y0 == null || y1 == null){
            throw new IllegalArgumentException("The params x0, x1, y0, y1 couldn't be null")
        }

        if (!isLine(x0, x1)){
            throw new IllegalArgumentException("The line (x0, x1) is malformed")
        }

        if (!isLine(y0, y1)){
            throw new IllegalArgumentException("The line (y0, y1) is malformed")
        }

        boolean intersection = false
        if (x0 <= y1 && x1 >= y0){
            intersection = true
        }
        return intersection
    }

    /**
     * Valida si la recta formada por los puntos (x0, x1) es una recta bien formada. x0 < x1.
     * El método arroja IllegalArgumentException si x0 y/o x1 son nulos
     * @param x0
     * @param x1
     * @return true si la recta está bien formada y false si está mal formada
     */
    public static boolean isLine(Number x0, Number x1){
        if (x0 == null || x1 == null){
            throw new IllegalArgumentException("The params x0, x1 couldn't be null")
        }
        return x0 < x1
    }
}
