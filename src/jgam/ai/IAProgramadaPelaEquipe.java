package jgam.ai;

import jgam.game.*;
import java.util.List;
import java.lang.Math;

public class IAProgramadaPelaEquipe extends EvaluatingAI {
    /**
     * evaluate a BoardSetup.
     * <p>
     * The result is an approximation of the probabiity to win.
     *
     * @param setup BoardSetup
     * @return double between 0 and 1.
     */


    /**
     * retorna a soma das pecas da ia
     *
     * <p>A soma das pecas da ia em cada casa sera poderada considerando
     * o avanco</p>
     *
     * <p>Até 270</p>
     *
     * @param setup
     * @return float greater or equal than 0
     */
    private float heuristicaAvanco(BoardSetup setup) {
        int player = setup.getActivePlayer();

        float pip = 0;
        for (int i = 1; i <= 25; i++) {
            pip += i * (-6.6914029106712682 + (3.2367315199142737 - 0.08274024712211484 * i)* i) * setup.getPoint(player, i);
        }

        return pip/8400;
    }
// Até 7
    private float heuristicaProtecao(BoardSetup setup) {
        int player = setup.getActivePlayer();

        float protegidos = 0;

        for (int i = 1; i < 25; i++) {
            if (setup.getPoint(player, i) > 1) {
                protegidos++;
            }
        }

        return protegidos/7;
    }
// Até 15
    private float heuristicaAtaque(BoardSetup setup) {
        int playerAtacante = setup.getActivePlayer();
        int playerDefensor = (playerAtacante == 1) ? 1: 2;

        return setup.getBar(playerDefensor)/15;
    }

    @Override
    public double propabilityToWin(BoardSetup setup) throws CannotDecideException {
        /* @todo só escreve aqui teu codigo */

        PossibleMoves possibleMoves = new PossibleMoves(setup);
        setup.getDice();

        return 0.4*heuristicaAtaque(setup) + 0.4*heuristicaProtecao(setup) + 0.2*heuristicaAvanco(setup);
    }

    /**
     * initialize this instance. Is called before it is used to
     * make decisions.
     *
     * @throws Exception if sth goes wrong during init.
     */
    @Override
    public void init() throws Exception {

    }

    /**
     * free all used resources.
     */
    @Override
    public void dispose() {

    }

    /**
     * get the name of this AI Method.
     *
     * @return String
     */
    @Override
    public String getName() {
        return "IA Radical";
    }

    /**
     * get a short description of this method.
     *
     * @return String
     */
    @Override
    public String getDescription() {
        return "Ia mais radical";
    }
}