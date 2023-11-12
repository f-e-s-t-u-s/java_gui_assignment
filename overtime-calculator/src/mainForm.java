import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Week
{
    public float[] days = new float[7];
    public Week(float[] days)
    {
        days = new float[7];
        System.arraycopy(days, 0, this.days, 0, 7);
    }
    public float calcRegular()
    {
        float totalHrs = 0f;
        for (float day : days) {
            if (day <= 8) {
                totalHrs += day;
            } else {
                totalHrs += 8;
            }
        }
        return totalHrs;
    }

    public float calcOvertime()
    {
        float totalHrs = 0f;
        for (float day : days) {
            if (day > 8) {
                totalHrs += day - 8;
            }
        }
        return totalHrs;
    }
};

public class mainForm extends JFrame{
    //Creating a week class
    private JPanel mainPanel;
    private JTextField empName;
    private JTextField monFw;
    private JTextField tueFw;
    private JTextField wedFw;
    private JTextField thuFw;
    private JTextField friFw;
    private JTextField satFw;
    private JTextField sunFw;
    private JTextField hourlySal;
    private JTextField monSw;
    private JTextField tueSw;
    private JTextField wedSw;
    private JTextField thuSw;
    private JTextField friSw;
    private JTextField satSw;
    private JTextField sunSw;
    private JButton processBtn;
    private JTextField regularHrs;
    private JTextField overtimeHrs;
    private JTextField regularPay;
    private JTextField overtimePay;
    private JTextField netPay;
    private JButton closeBtn;

    public mainForm() {
        //close button
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        //process button
        processBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Reading week1 data
                float[] hrsOne = {
                        Float.parseFloat(monFw.getText()),
                        Float.parseFloat(tueFw.getText()),
                        Float.parseFloat(wedFw.getText()),
                        Float.parseFloat(thuFw.getText()),
                        Float.parseFloat(friFw.getText()),
                        Float.parseFloat(satFw.getText()),
                        Float.parseFloat(sunFw.getText())
                };
                //Reading week2 data
                float[] hrsTwo = {
                        Float.parseFloat(monSw.getText()),
                        Float.parseFloat(tueSw.getText()),
                        Float.parseFloat(wedSw.getText()),
                        Float.parseFloat(thuSw.getText()),
                        Float.parseFloat(friSw.getText()),
                        Float.parseFloat(satSw.getText()),
                        Float.parseFloat(sunSw.getText())
                };

                //Creating week objects
                Week weekOne = new Week(new float[7]);
                Week weekTwo = new Week(new float[7]);
                //Copying arrays
                System.arraycopy(hrsOne, 0, weekOne.days, 0, 7);
                System.arraycopy(hrsTwo, 0, weekTwo.days, 0, 7);

                //Initializing some variables
                float hourlySalary = Float.parseFloat(hourlySal.getText());
                float regPay, ovrPay, ntPay, overtimeRate = 27.78f;

                //Performing the computation
                regPay = (weekOne.calcRegular() + weekTwo.calcRegular()) * hourlySalary;
                ovrPay = (weekOne.calcOvertime() + weekTwo.calcOvertime())* overtimeRate;
                ntPay = regPay + ovrPay;

                //Updating the UI elements
                regularHrs.setText(String.format("%.1f",  (regPay / hourlySalary)));
                overtimeHrs.setText(String.format("%.1f", (ovrPay / overtimeRate)));
                regularPay.setText(String.format("%.2f", regPay));
                overtimePay.setText(String.format("%.2f", ovrPay));
                netPay.setText(String.format("%.2f", ntPay));
            }
        });
    }

    public static void main(String[] args) {
        mainForm m = new mainForm();
        m.setContentPane(m.mainPanel);
        m.setTitle("Georgetown Cleaning Services - Employee Payroll");
        m.setSize(760, 350);
        m.setBounds(304, 209, 760, 350);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
