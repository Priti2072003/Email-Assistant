
# 📧 AI Email Assistant

An intelligent **Email Assistant** that helps users generate professional email replies instantly.
The system analyzes the **intent and tone of the received email** and generates an appropriate response automatically.

This project combines a **React frontend**, **Spring Boot backend**, and a **browser extension** to integrate AI-powered reply generation directly into the email workflow.

## 🚀 Features

* ✉️ **Automatic Email Reply Generation**
  Generates professional replies based on the incoming email content.

* 🧠 **Intent Detection**
  Identifies the purpose of the email (request, meeting, follow-up, information, etc.).

* 🎭 **Tone Analysis**
  Detects tone such as:

  * Casual
  * Friendly
  * Professional
  * Apologetic

* ⚡ **Instant Response Suggestions**
  Suggests ready-to-send replies in seconds.

* 🌐 **Browser Extension Integration**
  Works directly inside the email interface to assist users while replying.

* 🎯 **AI Powered**
  Uses AI/NLP models to understand the email context and generate smart responses.

---

## 🏗️ System Architecture

```
Browser Extension
        │
        ▼
React Frontend  ─────► Spring Boot Backend ─────► AI Model / API
        │
        ▼
Generated Email Reply
```

**Components:**

1. **React Frontend**

   * User interface
   * Displays generated replies
   * Communicates with backend APIs

2. **Spring Boot Backend**

   * Handles API requests
   * Processes email content
   * Calls AI services for reply generation

3. **Browser Extension**

   * Integrates with email platforms
   * Extracts email content
   * Sends it to the backend

---

## 🛠️ Tech Stack

### Frontend

* ⚛️ React
* Axios
* Material UI

### Backend

* ☕ Spring Boot
* REST APIs
* Maven

### Extension

* JavaScript
* Chrome Extension APIs

### AI Integration

* NLP / LLM API

---

## 📂 Project Structure

```
email-assistant/
│
├── frontend/              # React application
│   ├── src/
│   └── package.json
│
├── backend/               # Spring Boot application
│   ├── src/main/java
│   └── pom.xml
│
├── extension/             # Browser extension
│   ├── manifest.json
│   └── content.js
│
└── README.md
```

---

## ⚙️ Installation & Setup

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/Email-assistant.git
cd Email-assistant
```

---

### 2️⃣ Run Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

Backend will run on:

```
http://localhost:8080
```

---

### 3️⃣ Run Frontend (React)

```bash
cd frontend
npm install
npm start
```

Frontend will run on:

```
http://localhost:3000
```

---

### 4️⃣ Load Browser Extension

1. Open **Chrome**
2. Go to

```
chrome://extensions/
```

3. Enable **Developer Mode**
4. Click **Load Unpacked**
5. Select the **extension folder**

---

## 📸 Workflow

1. User opens email.
2. Browser extension extracts the email content.
3. Content is sent to the **Spring Boot backend**.
4. Backend processes **intent and tone**.
5. AI generates a **context-aware reply**.
6. Reply is displayed to the user in the interface.

---

## 🎯 Use Cases

* Professional email replies
* Customer support responses
* Business communication
* Quick reply generation
* Email productivity tools

---

## 🔮 Future Enhancements

* Multi-language support
* Gmail/Outlook direct integration
* Personalized writing style
* Sentiment-based reply improvement
* AI fine-tuning

---


