-- Drop the database if it already exists to start fresh
IF DB_ID('DuckMessages') IS NOT NULL
BEGIN
    ALTER DATABASE DuckMessages SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE DuckMessages;
END
GO

-- Create the new database
CREATE DATABASE DuckMessages;
GO

-- Switch to the newly created database context
USE DuckMessages;
GO

-- =================================================================
-- Users Table: Stores user account information
-- =================================================================
CREATE TABLE dbo.Users (
    UserID UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    DisplayName NVARCHAR(20) NOT NULL,
    PhoneNumber NVARCHAR(20) NOT NULL UNIQUE,
    PasswordHash BINARY(64) NOT NULL,
    PasswordSalt BINARY(32) NOT NULL,
    CreatedAt DATETIME2 NOT NULL DEFAULT GETUTCDATE()
);
GO

-- =================================================================
-- UserRelationships Table: Manages connections between users
-- =================================================================
CREATE TABLE dbo.UserRelationships (
    RelationshipID BIGINT PRIMARY KEY IDENTITY(1,1),
    UserID1 UNIQUEIDENTIFIER NOT NULL, -- The user who initiates the relationship
    UserID2 UNIQUEIDENTIFIER NOT NULL, -- The user who is the target of the relationship
    CreatedAt DATETIME2 NOT NULL DEFAULT GETUTCDATE(),

    -- Foreign key constraints to the Users table
    CONSTRAINT FK_Relationships_User1 FOREIGN KEY (UserID1) REFERENCES dbo.Users(UserID),
    CONSTRAINT FK_Relationships_User2 FOREIGN KEY (UserID2) REFERENCES dbo.Users(UserID),

    -- Prevent duplicate relationships between the same two users
    CONSTRAINT UQ_UserRelationshipPair UNIQUE (UserID1, UserID2)
);
GO

-- =================================================================
-- TextMessages Table: Stores individual text messages
-- =================================================================
CREATE TABLE dbo.TextMessages (
    MessageID BIGINT PRIMARY KEY IDENTITY(1,1),
    SenderID UNIQUEIDENTIFIER NOT NULL,
    ReceiverID UNIQUEIDENTIFIER NOT NULL,
    Content NVARCHAR(MAX) NOT NULL,
    SentAt DATETIME2 NOT NULL DEFAULT GETUTCDATE(),
    IsRead BIT NOT NULL DEFAULT 0,

    -- Foreign key constraints to the Users table
    CONSTRAINT FK_Messages_Sender FOREIGN KEY (SenderID) REFERENCES dbo.Users(UserID),
    CONSTRAINT FK_Messages_Receiver FOREIGN KEY (ReceiverID) REFERENCES dbo.Users(UserID)
);
GO