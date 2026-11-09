import java.util.Arrays;

/**
 * Model a 1D elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Automaton
{
    // The number of cells.
    private final int numberOfCells;
    // The state of the cells.
    private int[] state;
    
    /**
     * Create a 1D automaton consisting of the given number of cells.
     * @param numberOfCells The number of cells in the automaton.
     */
    public Automaton(int numberOfCells)
    {
        this.numberOfCells = numberOfCells;
        state = new int[numberOfCells];
        // Seed the automaton with a single 'on' cell in the middle.
        state[numberOfCells / 2] = 1;
    }
    
    /**
     * Print the current state of the automaton.
     */
    public void print()
    {
        for(int cellValue : state) {
            if(cellValue == 1) {
                System.out.print("*");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }   
    
    /**
     * Update the automaton to its next state.
     */
    public void update()
    {
       int[] nextState = new int[numberOfCells]; // Only need sizing for active cells
        int left = 0;
        int center = state[0];
    
        for (int i = 0; i < numberOfCells; i++) {
            int right = state[i + 1]; // Safe because state array is padded to length + 1
            nextState[i] = calculateNextState(left, center, right);
            left = center;
            center = right;
        }
    
    
        System.arraycopy(nextState, 0, state, 0, numberOfCells);
    } 
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        // Seed the automaton with a single 'on' cell.
        state[numberOfCells / 2] = 1;
    }
    private int calculateNextState(int left, int center, int right) {
        return (left + center + right) % 2;
    }
}
