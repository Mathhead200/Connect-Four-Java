package grid;


/**
 * GridItemIsDeadException is thrown when particular methods are invoked on a GridItem that has been killed.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see GridItem#kill()
 */
@SuppressWarnings("serial")
public class GridItemIsDeadException extends GridException
{

}
