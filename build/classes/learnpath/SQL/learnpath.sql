CREATE DATABASE IF NOT EXISTS db_learnpath;
USE db_learnpath;

CREATE TABLE IF NOT EXISTS users (
    userId VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL
);

CREATE TABLE rooms (
    roomId VARCHAR(50) PRIMARY KEY,
    roomName VARCHAR(100) NOT NULL,
    description TEXT,
    creatorUserId VARCHAR(50) NOT NULL,
    creationDate TIMESTAMP NOT NULL,
    memberCount INT,
    materialCount INT,
    taskCount INT,
    FOREIGN KEY (creatorUserId) REFERENCES users(userId)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE room_members (
    roomMemberId VARCHAR(50) PRIMARY KEY,
    userId VARCHAR(50) NOT NULL,
    roomId VARCHAR(50) NOT NULL,
    role VARCHAR(50) DEFAULT 'Member',
    FOREIGN KEY (userId) REFERENCES users(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (roomId) REFERENCES rooms(roomId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS schedules (
    scheduleId VARCHAR(50) PRIMARY KEY,
    userId VARCHAR(50) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    day VARCHAR(20),
    time TIME,
    roomId VARCHAR(50),
    FOREIGN KEY (userId) REFERENCES users(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (roomId) REFERENCES rooms(roomId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS materials (
    materialId VARCHAR(50) PRIMARY KEY,
    fileName VARCHAR(255) NOT NULL,
    fileLink TEXT,
    materialDetail TEXT,
    uploadTimestamp DATETIME,
    uploadedByUserId VARCHAR(50),
    roomId VARCHAR(50),
    subjectId VARCHAR(50),
    FOREIGN KEY (uploadedByUserId) REFERENCES users(userId)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (roomId) REFERENCES rooms(roomId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
    FOREIGN KEY (subjectId) REFERENCES subjects(subjectId)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS subjects (
    subjectId VARCHAR(50) PRIMARY KEY,
    subjectName VARCHAR(100),
    roomId VARCHAR(50),
    description TEXT
    FOREIGN KEY (roomId) REFERENCES rooms(roomId)
);

CREATE TABLE tasks (
    taskId VARCHAR(50) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    deadline TIMESTAMP,
    roomId VARCHAR(50),
    subjectId VARCHAR(50),
    creatorUserId VARCHAR(50),
    isCompleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (roomId) REFERENCES rooms(roomId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (subjectId) REFERENCES subjects(subjectId)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (creatorUserId) REFERENCES users(userId)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE friends (
    id VARCHAR(50) PRIMARY KEY,
    userId VARCHAR(50) NOT NULL,
    friendUserId VARCHAR(50) NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (friendUserId) REFERENCES users(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE group_chats (
    messageId VARCHAR(50) PRIMARY KEY,
    messageContent TEXT,
    timestamp DATETIME,
    senderUserId VARCHAR(50),
    roomId VARCHAR(50),
    FOREIGN KEY (senderUserId) REFERENCES users(userId)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (roomId) REFERENCES rooms(roomId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE personal_chats (
    messageId VARCHAR(50) PRIMARY KEY,
    messageContent TEXT,
    timestamp DATETIME,
    senderUserId VARCHAR(50),
    receiverUserId VARCHAR(50),
    FOREIGN KEY (senderUserId) REFERENCES users(userId)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (receiverUserId) REFERENCES users(userId)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
