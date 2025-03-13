import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Quiz {
    private String title;
    private String description;
    private List<Question> questions;

    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    // Display quiz information
    public void displayQuizInfo() {
        System.out.println("\n--- " + title + " ---");
        System.out.println(description);
        System.out.println("Number of questions: " + questions.size());
    }

    // Take the quiz and return results
    public QuizResult takeQuiz(Scanner scanner) {
        displayQuizInfo();
        System.out.println("\nPress enter to start the quiz...");
        scanner.nextLine();

        QuizResult result = new QuizResult();

        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("\nYour answer (A, B, C, D): ");
            char answer = scanner.nextLine().toUpperCase().charAt(0);

            // Validate input
            while (answer < 'A' || answer >= 'A' + question.getOptions().size()) {
                System.out.println("Invalid option. Please try again.");
                System.out.print("Your answer (A, B, C, D): ");
                answer = scanner.nextLine().toUpperCase().charAt(0);
            }

            Response response = new Response(question, answer);
            result.addResponse(response);

            System.out.println("Answer recorded: " + answer);
        }

        System.out.println("\nQuiz completed! Here are your results:");
        result.displayResults();

        return result;
    }

    // Create a climate change quiz
    public static Quiz createClimateChangeQuiz() {
        Quiz quiz = new Quiz("Climate Change Knowledge Quiz",
                "Test your knowledge about climate change and its impacts.");

        // Question 1
        List<String> q1Options = new ArrayList<>();
        q1Options.add("Carbon dioxide");
        q1Options.add("Methane");
        q1Options.add("Water vapor");
        q1Options.add("All of the above");
        quiz.addQuestion(new Question("Which of the following is a greenhouse gas?", q1Options, 3));

        // Question 2
        List<String> q2Options = new ArrayList<>();
        q2Options.add("1.5°C");
        q2Options.add("2.0°C");
        q2Options.add("2.5°C");
        q2Options.add("3.0°C");
        quiz.addQuestion(new Question("What is the global temperature increase limit set by the Paris Agreement?",
                q2Options, 1));

        // Question 3
        List<String> q3Options = new ArrayList<>();
        q3Options.add("Transportation");
        q3Options.add("Agriculture");
        q3Options.add("Electricity and heat production");
        q3Options.add("Buildings");
        quiz.addQuestion(new Question("Which sector contributes the most to global greenhouse gas emissions?",
                q3Options, 2));

        // Question 4
        List<String> q4Options = new ArrayList<>();
        q4Options.add("Burning fossil fuels");
        q4Options.add("Deforestation");
        q4Options.add("Industrial processes");
        q4Options.add("All of the above");
        quiz.addQuestion(new Question("What human activities contribute to climate change?",
                q4Options, 3));

        // Question 5
        List<String> q5Options = new ArrayList<>();
        q5Options.add("Rising sea levels");
        q5Options.add("More frequent extreme weather events");
        q5Options.add("Shifts in plant and animal ranges");
        q5Options.add("All of the above");
        quiz.addQuestion(new Question("What are the effects of climate change?",
                q5Options, 3));

        return quiz;
    }
}