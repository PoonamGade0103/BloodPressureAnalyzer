import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BloodPressureAnalyzer extends JFrame {

    private JTextField patientNoTextField, dateTextField, nameTextField,
            ageTextField, weightTextField, heightTextField, systolicTextField,
            diastolicTextField;
    private JCheckBox bpPatientCheckBox;
    private JComboBox<String> bloodGroupComboBox, genderComboBox;
    private JButton analyzeButton, resetButton;
    private JTextArea resultTextArea;

    public BloodPressureAnalyzer() {
        setTitle("Blood Pressure Analyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 750);
        setLocationRelativeTo(null);
        setLayout(null);

        // Labels
        JLabel patientNoLabel = new JLabel("Patient Number:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel genderLabel = new JLabel("Gender:");
        JLabel weightLabel = new JLabel("Weight:");
        JLabel heightLabel = new JLabel("Height:");
        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        JLabel bpPatientLabel = new JLabel("Are you a BP patient?");
        JLabel systolicLabel = new JLabel("Systolic Blood Pressure:");
        JLabel diastolicLabel = new JLabel("Diastolic Blood Pressure:");

        // TextFields
        patientNoTextField = new JTextField();
        dateTextField = new JTextField();
        nameTextField = new JTextField();
        ageTextField = new JTextField();
        weightTextField = new JTextField();
        heightTextField = new JTextField();
        systolicTextField = new JTextField();
        diastolicTextField = new JTextField();

        // ComboBoxes
        String[] bloodGroupList = {"A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-"};
        bloodGroupComboBox = new JComboBox<>(bloodGroupList);

        String[] genderList = {"Male", "Female", "Others"};
        genderComboBox = new JComboBox<>(genderList);

        // CheckBox
        bpPatientCheckBox = new JCheckBox();

        // Buttons
        analyzeButton = new JButton("Analyze");
        resetButton = new JButton("Reset");

        // TextArea with ScrollPane
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBounds(30, 500, 620, 180);

        // Set bounds for Labels
        patientNoLabel.setBounds(30, 30, 150, 25);
        dateLabel.setBounds(30, 70, 150, 25);
        nameLabel.setBounds(30, 110, 150, 25);
        ageLabel.setBounds(30, 150, 150, 25);
        genderLabel.setBounds(30, 190, 150, 25);
        weightLabel.setBounds(30, 230, 150, 25);
        heightLabel.setBounds(30, 270, 150, 25);
        bloodGroupLabel.setBounds(30, 310, 150, 25);
        bpPatientLabel.setBounds(30, 340, 180, 25);
        systolicLabel.setBounds(30, 380, 180, 25);
        diastolicLabel.setBounds(30, 420, 180, 25);

        // Set bounds for TextFields, ComboBoxes, CheckBox
        patientNoTextField.setBounds(220, 30, 200, 25);
        dateTextField.setBounds(220, 70, 200, 25);
        nameTextField.setBounds(220, 110, 200, 25);
        ageTextField.setBounds(220, 150, 200, 25);
        genderComboBox.setBounds(220, 190, 200, 25);
        weightTextField.setBounds(220, 230, 200, 25);
        heightTextField.setBounds(220, 270, 200, 25);
        bloodGroupComboBox.setBounds(220, 310, 200, 25);
        bpPatientCheckBox.setBounds(220, 340, 50, 25);
        systolicTextField.setBounds(220, 380, 200, 25);
        diastolicTextField.setBounds(220, 420, 200, 25);

        // Set bounds for Buttons
        analyzeButton.setBounds(220, 460, 100, 30);
        resetButton.setBounds(340, 460, 100, 30);

        // Add components
        add(patientNoLabel); add(dateLabel); add(nameLabel); add(ageLabel);
        add(genderLabel); add(weightLabel); add(heightLabel); add(bloodGroupLabel);
        add(bpPatientLabel); add(systolicLabel); add(diastolicLabel);

        add(patientNoTextField); add(dateTextField); add(nameTextField); add(ageTextField);
        add(genderComboBox); add(weightTextField); add(heightTextField); add(bloodGroupComboBox);
        add(bpPatientCheckBox); add(systolicTextField); add(diastolicTextField);

        add(analyzeButton); add(resetButton); add(scrollPane);

        // Action Listeners
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzeBloodPressure();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        setVisible(true);
    }

    // Analyze BP
    private void analyzeBloodPressure() {
        try {
            if (systolicTextField.getText().isEmpty() || diastolicTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter blood pressure values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int systolic = Integer.parseInt(systolicTextField.getText());
            int diastolic = Integer.parseInt(diastolicTextField.getText());

            String result = "Results:\n";

            if (systolic < 120 && diastolic < 80) {
                result += "Normal Blood Pressure.\nRemedies: Maintain a healthy lifestyle and monitor regularly.";
            } else if (systolic >= 120 && systolic < 130 && diastolic < 80) {
                result += "Elevated Blood Pressure.\nRemedies: Lifestyle changes recommended.";
            } else if ((systolic >= 130 && systolic < 140) || (diastolic >= 80 && diastolic < 90)) {
                result += "Hypertension Stage 1.\nRemedies: Consult a doctor, adopt lifestyle changes.";
            } else if ((systolic >= 140 && systolic < 180) || (diastolic >= 90 && diastolic < 120)) {
                result += "Hypertension Stage 2.\nRemedies: Medical treatment required.";
            } else if (systolic >= 180 || diastolic >= 120) {
                result += "Hypertensive Crisis!\nRemedies: Seek emergency medical attention immediately!";
            }

            resultTextArea.setText(result);
        } catch (NumberFormatException e) {
            resultTextArea.setText("Invalid input. Please enter numeric values for blood pressure.");
        }
    }

    // Reset fields
    private void resetFields() {
        patientNoTextField.setText("");
        dateTextField.setText("");
        nameTextField.setText("");
        ageTextField.setText("");
        weightTextField.setText("");
        heightTextField.setText("");
        systolicTextField.setText("");
        diastolicTextField.setText("");
        genderComboBox.setSelectedIndex(0);
        bloodGroupComboBox.setSelectedIndex(0);
        bpPatientCheckBox.setSelected(false);
        resultTextArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BloodPressureAnalyzer());
    }
}
