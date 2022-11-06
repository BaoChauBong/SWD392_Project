USE [master]
GO
/****** Object:  Database [Project_SWD]    Script Date: 06/11/2022 07:01:00 AM ******/
CREATE DATABASE [Project_SWD]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Project_SWD', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Project_SWD.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Project_SWD_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Project_SWD_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Project_SWD] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Project_SWD].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Project_SWD] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Project_SWD] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Project_SWD] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Project_SWD] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Project_SWD] SET ARITHABORT OFF 
GO
ALTER DATABASE [Project_SWD] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Project_SWD] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Project_SWD] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Project_SWD] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Project_SWD] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Project_SWD] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Project_SWD] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Project_SWD] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Project_SWD] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Project_SWD] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Project_SWD] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Project_SWD] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Project_SWD] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Project_SWD] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Project_SWD] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Project_SWD] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Project_SWD] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Project_SWD] SET RECOVERY FULL 
GO
ALTER DATABASE [Project_SWD] SET  MULTI_USER 
GO
ALTER DATABASE [Project_SWD] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Project_SWD] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Project_SWD] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Project_SWD] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Project_SWD] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Project_SWD] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Project_SWD', N'ON'
GO
ALTER DATABASE [Project_SWD] SET QUERY_STORE = OFF
GO
USE [Project_SWD]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 06/11/2022 07:01:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [nvarchar](30) NOT NULL,
	[password] [varchar](20) NOT NULL,
	[firstname] [nvarchar](30) NOT NULL,
	[lastname] [nvarchar](30) NOT NULL,
	[email] [varchar](30) NOT NULL,
	[roleId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 06/11/2022 07:01:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleId] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'abc', N'aaa', N'aaa', N'aaa', N'aaa', 1)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'admin', N'Nvbc@11', N'chau', N'nguyen', N'baochau41201gmail.com', 1)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'admin21312', N'Nvbc2001@', N'khsadfj', N'jhsdgfljas', N'baochau41201@gmail.com', 6)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'admin22', N'Nvbc2001@', N'chaudjh', N'jgjfdlghd', N'baochau41201@gmail.com', 6)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'admin3123', N'Nvbc2001@', N'djsghsdg', N'sfdgdafhdhf', N'baochau41201@gmail.com', 3)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'admin333', N'Nvbc2001@', N'hdshdsgh', N'dfhdsfhds', N'baochau41201@gmail.com', 2)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'designertester', N'designertester', N'tan', N'ta', N'tantagmail.com', 4)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'leader', N'Nvbc2001@', N'hai', N'luu hoang', N'hailuu@gmail.com', 2)
INSERT [dbo].[Account] ([username], [password], [firstname], [lastname], [email], [roleId]) VALUES (N'test', N'Nvbc2001@', N'Chau', N'nguyen', N'baochau41201@gmail.com', 3)
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([roleId], [roleName]) VALUES (1, N'Adminstrator')
INSERT [dbo].[Role] ([roleId], [roleName]) VALUES (2, N'Leader')
INSERT [dbo].[Role] ([roleId], [roleName]) VALUES (3, N'Tester')
INSERT [dbo].[Role] ([roleId], [roleName]) VALUES (4, N'Designer Tester')
INSERT [dbo].[Role] ([roleId], [roleName]) VALUES (5, N'Senior Tester')
INSERT [dbo].[Role] ([roleId], [roleName]) VALUES (6, N'Guest')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
USE [master]
GO
ALTER DATABASE [Project_SWD] SET  READ_WRITE 
GO
