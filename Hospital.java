import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class Hospital
{
    static Connection connection;

    static String url = "jdbc:mysql://localhost:3306/mydb";
    static String username = "root";
    static String password = "YOUR_MYSQL_PASSWORD";

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        try
        {
            // CONNECT TO MYSQL
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("MySQL connected successfully!");

            int choice = 1;

            while(choice != 0)
            {
                System.out.println("\n=================================");
                System.out.println("        CITYCARE HOSPITAL");
                System.out.println("=================================");
                System.out.println("1. Register New Patient");
                System.out.println("2. Search Existing Patient");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                if(choice == 1)
                {
                    registerPatient(sc);
                }
                else if(choice == 2)
                {
                    searchPatient(sc);
                }
                else if(choice == 0)
                {
                    System.out.println("\nThank you for visiting CityCare Hospital.");
                }
                else
                {
                    System.out.println("\nInvalid choice.");
                }
            }

            connection.close();
            sc.close();
        }
        catch(SQLException e)
        {
            System.out.println("Database connection error.");
            System.out.println(e.getMessage());
        }
    }


    // =========================================================
    // REGISTER NEW PATIENT
    // =========================================================

    static void registerPatient(Scanner sc)
    {
        System.out.println("\n=================================");
        System.out.println("       NEW PATIENT REGISTRATION");
        System.out.println("=================================");

        System.out.print("Enter patient's first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter patient's last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter gender (M/F): ");
        char gender = sc.next().charAt(0);
        sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        try
        {
            String sql =
                "INSERT INTO patients " +
                "(patient_firstname, patient_lastname, age, gender, phone_number) " +
                "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement =
                connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
                );

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.setString(4, String.valueOf(gender));
            statement.setString(5, phone);

            statement.executeUpdate();

            // GET AUTO-GENERATED PATIENT ID
            ResultSet keys = statement.getGeneratedKeys();

            int patientId = 0;

            if(keys.next())
            {
                patientId = keys.getInt(1);
            }

            System.out.println("\nPatient registered successfully!");
            System.out.println("Patient ID: " + patientId);

            statement.close();
            keys.close();

            // CONTINUE WITH VISIT
            processVisit(
                sc,
                patientId,
                firstName + " " + lastName,
                age,
                gender,
                phone
            );
        }
        catch(SQLException e)
        {
            System.out.println("Error registering patient.");
            System.out.println(e.getMessage());
        }
    }


    // =========================================================
    // SEARCH EXISTING PATIENT
    // =========================================================

    static void searchPatient(Scanner sc)
    {
        System.out.println("\n=================================");
        System.out.println("       SEARCH EXISTING PATIENT");
        System.out.println("=================================");

        System.out.print("Enter patient's phone number: ");
        String phone = sc.nextLine();

        try
        {
            String sql =
                "SELECT * FROM patients WHERE phone_number = ?";

            PreparedStatement statement =
                connection.prepareStatement(sql);

            statement.setString(1, phone);

            ResultSet result = statement.executeQuery();

            if(result.next())
            {
                int patientId = result.getInt("patient_id");
                String firstName = result.getString("patient_firstname");
                String lastName = result.getString("patient_lastname");
                int age = result.getInt("age");
                char gender = result.getString("gender").charAt(0);

                System.out.println("\nPatient found!");
                System.out.println("-----------------------------");
                System.out.println("Patient ID : " + patientId);
                System.out.println("Name       : " + firstName + " " + lastName);
                System.out.println("Age        : " + age);
                System.out.println("Gender     : " + gender);
                System.out.println("Phone      : " + phone);
                System.out.println("-----------------------------");

                System.out.print("\nDoes this patient want an appointment? (Y/N): ");
                char answer = sc.next().charAt(0);
                sc.nextLine();

                if(answer == 'Y' || answer == 'y')
                {
                    processVisit(
                        sc,
                        patientId,
                        firstName + " " + lastName,
                        age,
                        gender,
                        phone
                    );
                }
            }
            else
            {
                System.out.println("\nPatient not found.");
            }

            result.close();
            statement.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error searching for patient.");
            System.out.println(e.getMessage());
        }
    }


    // =========================================================
    // PROCESS CURRENT VISIT
    // =========================================================

    static void processVisit(
        Scanner sc,
        int patientId,
        String patientName,
        int age,
        char gender,
        String phone)
    {
        System.out.println("\n=================================");
        System.out.println("          PATIENT VISIT");
        System.out.println("=================================");

        System.out.print("Enter patient's problem/symptoms: ");
        String problem = sc.nextLine();

        System.out.print("Enter patient's weakness: ");
        String weakness = sc.nextLine();

        // DEPARTMENT
        System.out.println("\nSelect the appropriate problem:");
        System.out.println("1. Fever / Cold");
        System.out.println("2. Tooth Pain");
        System.out.println("3. Eye Problem");
        System.out.println("4. Bone / Joint Pain");
        System.out.println("5. Skin Problem");
        System.out.println("6. Heart-related Problem");
        System.out.println("7. Stomach Problem");
        System.out.println("8. General Consultation");

        System.out.print("Enter choice: ");
        int departmentChoice = sc.nextInt();
        sc.nextLine();

        String department;

        switch(departmentChoice)
        {
            case 1:
                department = "General Medicine";
                break;

            case 2:
                department = "Dental Department";
                break;

            case 3:
                department = "Ophthalmology Department";
                break;

            case 4:
                department = "Orthopaedics Department";
                break;

            case 5:
                department = "Dermatology Department";
                break;

            case 6:
                department = "Cardiology Department";
                break;

            case 7:
                department = "Gastroenterology Department";
                break;

            case 8:
                department = "General Consultation";
                break;

            default:
                System.out.println("Invalid department choice.");
                return;
        }

        // FIND DOCTOR
        int doctorId = 0;
        String doctorName = "";

        try
        {
            String sql =
                "SELECT id, first_name, last_name " +
                "FROM doctors WHERE department = ?";

            PreparedStatement statement =
                connection.prepareStatement(sql);

            statement.setString(1, department);

            ResultSet result = statement.executeQuery();

            if(result.next())
            {
                doctorId = result.getInt("id");

                String doctorFirstName =
                    result.getString("first_name");

                String doctorLastName =
                    result.getString("last_name");

                doctorName =
                    doctorFirstName + " " + doctorLastName;

                System.out.println("\n=================================");
                System.out.println("          DOCTOR DETAILS");
                System.out.println("=================================");
                System.out.println("Department : " + department);
                System.out.println("Doctor     : " + doctorName);
                System.out.println(
                    "Please go to the " +
                    department +
                    " doctor's office."
                );
            }
            else
            {
                System.out.println(
                    "\nNo doctor found for this department."
                );

                result.close();
                statement.close();
                return;
            }

            result.close();
            statement.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error finding doctor.");
            System.out.println(e.getMessage());
            return;
        }


        // =====================================================
        // APPOINTMENT
        // =====================================================

        System.out.print(
            "\nEnter appointment date (YYYY-MM-DD): "
        );

        String appointmentDate = sc.nextLine();

        try
        {
            Date date = Date.valueOf(appointmentDate);

            String sql =
                "INSERT INTO appointments " +
                "(patient_id, doctor_id, appointment_date) " +
                "VALUES (?, ?, ?)";

            PreparedStatement statement =
                connection.prepareStatement(sql);

            statement.setInt(1, patientId);
            statement.setInt(2, doctorId);
            statement.setDate(3, date);

            statement.executeUpdate();

            System.out.println(
                "\nAppointment saved successfully!"
            );

            System.out.println("Patient ID : " + patientId);
            System.out.println("Doctor ID  : " + doctorId);
            System.out.println("Doctor     : " + doctorName);
            System.out.println("Date       : " + appointmentDate);

            statement.close();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(
                "Invalid date. Please use YYYY-MM-DD."
            );
            return;
        }
        catch(SQLException e)
        {
            System.out.println("Error saving appointment.");
            System.out.println(e.getMessage());
            return;
        }


        // =====================================================
        // PAYMENT
        // =====================================================

        int consultationFee = 300;

        System.out.println(
            "\nConsultation Fee: Rs. " +
            consultationFee
        );

        System.out.print("Enter amount paid: ");
        int paid = sc.nextInt();
        sc.nextLine();

        if(paid < consultationFee)
        {
            System.out.println(
                "\nInsufficient payment."
            );

            System.out.println(
                "Receipt cannot be generated."
            );

            return;
        }

        int change = paid - consultationFee;

        System.out.println("\nPayment successful.");
        System.out.println("Change: Rs. " + change);


        // =====================================================
        // CONSOLE RECEIPT
        // =====================================================

        System.out.println(
            "\n========================================"
        );

        System.out.println(
            "          CITYCARE HOSPITAL"
        );

        System.out.println(
            "       123 Park Street, Kolkata"
        );

        System.out.println(
            "========================================"
        );

        System.out.println(
            "              RECEIPT"
        );

        System.out.println(
            "----------------------------------------"
        );

        System.out.println(
            "Patient ID   : " + patientId
        );

        System.out.println(
            "Patient Name : " + patientName
        );

        System.out.println(
            "Phone Number : " + phone
        );

        System.out.println(
            "Age          : " + age
        );

        System.out.println(
            "Gender       : " + gender
        );

        System.out.println(
            "Problem      : " + problem
        );

        System.out.println(
            "Weakness     : " + weakness
        );

        System.out.println(
            "Department   : " + department
        );

        System.out.println(
            "Doctor       : " + doctorName
        );

        System.out.println(
            "Appointment  : " + appointmentDate
        );

        System.out.println(
            "----------------------------------------"
        );

        System.out.println(
            "Consultation Fee : Rs. " +
            consultationFee
        );

        System.out.println(
            "Amount Paid      : Rs. " +
            paid
        );

        System.out.println(
            "Change           : Rs. " +
            change
        );

        System.out.println(
            "----------------------------------------"
        );

        System.out.println(
            "Doctor's Prescription:"
        );

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(
            "----------------------------------------"
        );

        System.out.println(
            "       Thank you for visiting!"
        );

        System.out.println(
            "========================================"
        );


        // =====================================================
        // CREATE HTML RECEIPT
        // =====================================================

        createHTMLReceipt(
            patientId,
            patientName,
            phone,
            age,
            gender,
            problem,
            weakness,
            department,
            doctorName,
            appointmentDate,
            consultationFee,
            paid,
            change
        );
    }


    // =========================================================
    // CREATE HTML RECEIPT
    // =========================================================

    static void createHTMLReceipt(
        int patientId,
        String patientName,
        String phone,
        int age,
        char gender,
        String problem,
        String weakness,
        String department,
        String doctorName,
        String appointmentDate,
        int consultationFee,
        int paid,
        int change)
    {
        try
        {
            FileWriter fw =
                new FileWriter("receipt.html");

            fw.write("<html>");
            fw.write("<head>");

            fw.write(
                "<title>CityCare Hospital Receipt</title>"
            );

            fw.write("<style>");

            fw.write(
                "body { " +
                "font-family: Arial; " +
                "background-color: #f2f2f2; " +
                "}"
            );

            fw.write(
                ".receipt { " +
                "width: 650px; " +
                "margin: 40px auto; " +
                "background: white; " +
                "padding: 30px; " +
                "border: 2px solid black; " +
                "}"
            );

            fw.write(
                "h1 { " +
                "text-align: center; " +
                "margin-bottom: 5px; " +
                "}"
            );

            fw.write(
                "h2 { " +
                "text-align: center; " +
                "}"
            );

            fw.write(
                "p { " +
                "font-size: 16px; " +
                "}"
            );

            fw.write(
                "table { " +
                "width: 100%; " +
                "border-collapse: collapse; " +
                "}"
            );

            fw.write(
                "td { " +
                "padding: 8px; " +
                "border-bottom: 1px solid #ddd; " +
                "}"
            );

            fw.write(
                ".prescription { " +
                "height: 120px; " +
                "border: 1px solid black; " +
                "}"
            );

            fw.write("</style>");

            fw.write("</head>");
            fw.write("<body>");

            fw.write("<div class='receipt'>");

            fw.write(
                "<h1>CITYCARE HOSPITAL</h1>"
            );

            fw.write(
                "<p style='text-align:center;'>" +
                "123 Park Street, Kolkata" +
                "</p>"
            );

            fw.write("<hr>");

            fw.write("<h2>RECEIPT</h2>");

            fw.write("<table>");

            fw.write(
                "<tr><td><b>Patient ID</b></td>" +
                "<td>" + patientId + "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Patient Name</b></td>" +
                "<td>" + htmlEscape(patientName) +
                "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Phone Number</b></td>" +
                "<td>" + htmlEscape(phone) +
                "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Age</b></td>" +
                "<td>" + age + "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Gender</b></td>" +
                "<td>" + gender + "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Problem</b></td>" +
                "<td>" + htmlEscape(problem) +
                "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Weakness</b></td>" +
                "<td>" + htmlEscape(weakness) +
                "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Department</b></td>" +
                "<td>" + htmlEscape(department) +
                "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Doctor</b></td>" +
                "<td>" + htmlEscape(doctorName) +
                "</td></tr>"
            );

            fw.write(
                "<tr><td><b>Appointment Date</b></td>" +
                "<td>" + appointmentDate +
                "</td></tr>"
            );

            fw.write("</table>");

            fw.write("<hr>");

            fw.write(
                "<p><b>Consultation Fee:</b> Rs. " +
                consultationFee +
                "</p>"
            );

            fw.write(
                "<p><b>Amount Paid:</b> Rs. " +
                paid +
                "</p>"
            );

            fw.write(
                "<p><b>Change:</b> Rs. " +
                change +
                "</p>"
            );

            fw.write(
                "<h3>Doctor's Prescription</h3>"
            );

            fw.write(
                "<div class='prescription'></div>"
            );

            fw.write("<br>");

            fw.write(
                "<p style='text-align:center;'>" +
                "Thank you for visiting CityCare Hospital!" +
                "</p>"
            );

            fw.write("</div>");

            fw.write("</body>");
            fw.write("</html>");

            fw.close();

            File receipt =
                new File("receipt.html");

            System.out.println(
                "\nHTML receipt created successfully!"
            );

            System.out.println(
                "Receipt location:"
            );

            System.out.println(
                receipt.getAbsolutePath()
            );
        }
        catch(IOException e)
        {
            System.out.println(
                "Error creating HTML receipt."
            );

            System.out.println(
                e.getMessage()
            );
        }
    }


    // =========================================================
    // HTML ESCAPE
    // =========================================================

    static String htmlEscape(String text)
    {
        return text
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
    }
}