USE [master]
GO
/****** Object:  Database [COMP228-F2016-OnlineGameTracker]    Script Date: 2016-12-01 5:55:02 PM ******/
CREATE DATABASE [COMP228-F2016-OnlineGameTracker]

GO
USE [COMP228-F2016-OnlineGameTracker]
GO
/****** Object:  Sequence [dbo].[Game_game_id_Sequence]    Script Date: 2016-12-01 5:55:02 PM ******/
CREATE SEQUENCE [dbo].[Game_game_id_Sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE  10 
GO
USE [COMP228-F2016-OnlineGameTracker]
GO
/****** Object:  Sequence [dbo].[Player_player_id_Sequence]    Script Date: 2016-12-01 5:55:02 PM ******/
CREATE SEQUENCE [dbo].[Player_player_id_Sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE  10 
GO
USE [COMP228-F2016-OnlineGameTracker]
GO
/****** Object:  Sequence [dbo].[PlayerAndGame_player_game_id_Sequence]    Script Date: 2016-12-01 5:55:02 PM ******/
CREATE SEQUENCE [dbo].[PlayerAndGame_player_game_id_Sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE  10 
GO
/****** Object:  Table [dbo].[Game]    Script Date: 2016-12-01 5:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Game](
	[game_id] [int] NOT NULL,
	[game_title] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[game_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Player]    Script Date: 2016-12-01 5:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Player](
	[player_id] [int] NOT NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[address] [nvarchar](50) NOT NULL,
	[postal_code] [nvarchar](50) NOT NULL,
	[province] [nvarchar](50) NOT NULL,
	[phone_number] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[player_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PlayerAndGame]    Script Date: 2016-12-01 5:55:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlayerAndGame](
	[player_game_id] [int] NOT NULL,
	[game_id] [int] NOT NULL,
	[player_id] [int] NOT NULL,
	[playing_date] [date] NOT NULL,
	[score] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[player_game_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Game] ADD  DEFAULT (NEXT VALUE FOR [Game_game_id_Sequence]) FOR [game_id]
GO
ALTER TABLE [dbo].[Player] ADD  DEFAULT (NEXT VALUE FOR [dbo].[Player_player_id_Sequence]) FOR [player_id]
GO
ALTER TABLE [dbo].[PlayerAndGame] ADD  DEFAULT (NEXT VALUE FOR [dbo].[PlayerAndGame_player_game_id_Sequence]) FOR [player_game_id]
GO
ALTER TABLE [dbo].[PlayerAndGame]  WITH CHECK ADD  CONSTRAINT [FK_PlayerAndGame_ToGame] FOREIGN KEY([game_id])
REFERENCES [dbo].[Game] ([game_id])
GO
ALTER TABLE [dbo].[PlayerAndGame] CHECK CONSTRAINT [FK_PlayerAndGame_ToGame]
GO
ALTER TABLE [dbo].[PlayerAndGame]  WITH CHECK ADD  CONSTRAINT [FK_PlayerAndGame_ToPlayer] FOREIGN KEY([player_id])
REFERENCES [dbo].[Player] ([player_id])
GO
ALTER TABLE [dbo].[PlayerAndGame] CHECK CONSTRAINT [FK_PlayerAndGame_ToPlayer]
GO
USE [master]
GO
ALTER DATABASE [COMP228-F2016-OnlineGameTracker] SET  READ_WRITE 
GO
