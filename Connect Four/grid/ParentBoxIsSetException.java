package grid;


/**
 * ParentBoxIsSetException is thrown when a GridItem attempts to add itself to a Box, but has already
 * been previously added to one.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see GridItem#removeFromGrid()
 */
@SuppressWarnings("serial")
public class ParentBoxIsSetException extends GridException
{

}
