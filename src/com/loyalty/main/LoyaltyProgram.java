package com.loyalty.main;
import java.sql.*;
import java.util.Scanner;

public class LoyaltyProgram {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Customer Loyalty Program");
            System.out.println("1. Customer Management");
            System.out.println("2. Reward Management");
            System.out.println("3. Purchase Management");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    customerManagement();
                    break;
                case 2:
                    rewardManagement();
                    break;
                case 3:
                    purchaseManagement();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Customer Management
    private static void customerManagement() {
        System.out.println("Customer Management");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addCustomer();
                break;
            case 2:
                viewCustomer();
                break;
            case 3:
                updateCustomer();
                break;
            case 4:
                deleteCustomer();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void addCustomer() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter name: ");
            String name = scanner.next();
            System.out.print("Enter email: ");
            String email = scanner.next();
            System.out.print("Enter phone: ");
            String phone = scanner.next();

            String sql = "INSERT INTO Customer (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.executeUpdate();

            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    private static void viewCustomer() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();

            String sql = "SELECT * FROM Customer WHERE customer_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Total Points: " + rs.getInt("total_points"));
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing customer: " + e.getMessage());
        }
    }

    private static void updateCustomer() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();

            String sql = "SELECT * FROM Customer WHERE customer_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.print("Enter new name: ");
                String name = scanner.next();
                System.out.print("Enter new email: ");
                String email = scanner.next();
                System.out.print("Enter new phone: ");
                String phone = scanner.next();

                sql = "UPDATE Customer SET name = ?, email = ?, phone = ? WHERE customer_id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, email);
                pst.setString(3, phone);
                pst.setInt(4, customerId);
                pst.executeUpdate();

                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    private static void deleteCustomer() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();

            String sql = "DELETE FROM Customer WHERE customer_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customerId);
            int rowsDeleted = pst.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    // Reward Management
    private static void rewardManagement() {
        System.out.println("Reward Management");
        System.out.println("1. Add Reward");
        System.out.println("2. View Reward");
        System.out.println("3. Update Reward");
        System.out.println("4. Delete Reward");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addReward();
                break;
            case 2:
                viewReward();
                break;
            case 3:
                updateReward();
                break;
            case 4:
                deleteReward();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void addReward() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter reward name: ");
            String name = scanner.next();
            System.out.print("Enter points required: ");
            int pointsRequired = scanner.nextInt();
            System.out.print("Enter description: ");
            String description = scanner.next();

            String sql = "INSERT INTO Reward (name, points_required, description) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, pointsRequired);
            pst.setString(3, description);
            pst.executeUpdate();

            System.out.println("Reward added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding reward: " + e.getMessage());
        }
    }

    private static void viewReward() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();

            String sql = "SELECT * FROM Reward WHERE reward_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, rewardId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Reward ID: " + rs.getInt("reward_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Points Required: " + rs.getInt("points_required"));
                System.out.println("Description: " + rs.getString("description"));
            } else {
                System.out.println("Reward not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing reward: " + e.getMessage());
        }
    }

    private static void updateReward() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();

            String sql = "SELECT * FROM Reward WHERE reward_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, rewardId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.print("Enter new name: ");
                System.out.print("Enter new name: ");
                String name = scanner.next();
                System.out.print("Enter new points required: ");
                int pointsRequired = scanner.nextInt();
                System.out.print("Enter new description: ");
                String description = scanner.next();

                sql = "UPDATE Reward SET name = ?, points_required = ?, description = ? WHERE reward_id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, pointsRequired);
                pst.setString(3, description);
                pst.setInt(4, rewardId);
                pst.executeUpdate();

                System.out.println("Reward updated successfully.");
            } else {
                System.out.println("Reward not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating reward: " + e.getMessage());
        }
    }

    private static void deleteReward() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();

            String sql = "DELETE FROM Reward WHERE reward_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, rewardId);
            int rowsDeleted = pst.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Reward deleted successfully.");
            } else {
                System.out.println("Reward not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting reward: " + e.getMessage());
        }
    }

    // Purchase Management
    private static void purchaseManagement() {
        System.out.println("Purchase Management");
        System.out.println("1. Add Purchase");
        System.out.println("2. View Purchase");
        System.out.println("3. Update Purchase");
        System.out.println("4. Delete Purchase");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addPurchase();
                break;
            case 2:
                viewPurchase();
                break;
            case 3:
                updatePurchase();
                break;
            case 4:
                deletePurchase();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void addPurchase() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();
            System.out.print("Enter points earned: ");
            int pointsEarned = scanner.nextInt();

            String sql = "INSERT INTO Purchase (customer_id, reward_id, purchase_date, points_earned) VALUES (?, ?, CURDATE(), ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customerId);
            pst.setInt(2, rewardId);
            pst.setInt(3, pointsEarned);
            pst.executeUpdate();

            sql = "UPDATE Customer SET total_points = total_points + ? WHERE customer_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, pointsEarned);
            pst.setInt(2, customerId);
            pst.executeUpdate();

            System.out.println("Purchase added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding purchase: " + e.getMessage());
        }
    }

    private static void viewPurchase() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter purchase ID: ");
            int purchaseId = scanner.nextInt();

            String sql = "SELECT * FROM Purchase WHERE purchase_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, purchaseId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Purchase ID: " + rs.getInt("purchase_id"));
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Reward ID: " + rs.getInt("reward_id"));
                System.out.println("Purchase Date: " + rs.getDate("purchase_date"));
                System.out.println("Points Earned: " + rs.getInt("points_earned"));
            } else {
                System.out.println("Purchase not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing purchase: " + e.getMessage());
        }
    }

    private static void updatePurchase() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter purchase ID: ");
            int purchaseId = scanner.nextInt();

            String sql = "SELECT * FROM Purchase WHERE purchase_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, purchaseId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.print("Enter new points earned: ");
                int pointsEarned = scanner.nextInt();

                int oldPointsEarned = rs.getInt("points_earned");
                int customerId = rs.getInt("customer_id");

                sql = "UPDATE Purchase SET points_earned = ? WHERE purchase_id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, pointsEarned);
                pst.setInt(2, purchaseId);
                pst.executeUpdate();

                sql = "UPDATE Customer SET total_points = total_points + ? - ? WHERE customer_id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, pointsEarned);
                pst.setInt(2, oldPointsEarned);
                pst.setInt(3, customerId);
                pst.executeUpdate();

                System.out.println("Purchase updated successfully.");
            } else {
                System.out.println("Purchase not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating purchase: " + e.getMessage());
        }
    }

    private static void deletePurchase() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter purchase ID: ");
            int purchaseId = scanner.nextInt();

            String sql = "SELECT * FROM Purchase WHERE purchase_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, purchaseId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int pointsEarned = rs.getInt("points_earned");
                int customerId = rs.getInt("customer_id");

                sql = "DELETE FROM Purchase WHERE purchase_id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, purchaseId);
                pst.executeUpdate();

                sql = "UPDATE Customer SET total_points = total_points - ? WHERE customer_id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, pointsEarned);
                pst.setInt(2, customerId);
                pst.executeUpdate();

                System.out.println("Purchase deleted successfully.");
            } else {
                System.out.println("Purchase not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting purchase: " + e.getMessage());
        }
    }
}

