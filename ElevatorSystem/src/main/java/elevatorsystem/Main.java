package elevatorsystem;

import java.util.TreeSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TreeSet<Integer> st = new TreeSet<>();
        for (int i = 1; i < 11; i++) {
            st.add(i);
        }

        Integer a = st.ceiling(12);
        Integer b = st.floor(0);
        int just = 1;

    }
}