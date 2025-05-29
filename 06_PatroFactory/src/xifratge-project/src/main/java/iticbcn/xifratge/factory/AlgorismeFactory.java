package iticbcn.xifratge.factory;

import iticbcn.xifratge.Xifrador;

/**
 * Clase abstracta que define el patrón Factory.
 * Sirve como base para crear diferentes tipos de cifradores.
 */
public abstract class AlgorismeFactory {

    /**
     * Método abstracto que deben implementar todas las factorías concretas.
     * Devuelve una instancia de un cifrador específico.
     *
     * @return Una implementación de la interfaz Xifrador
     */
    public abstract Xifrador creaXifrador();
}