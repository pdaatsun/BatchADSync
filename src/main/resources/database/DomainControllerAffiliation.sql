USE [ADUsers]
GO

/****** Object:  Table [dbo].[[DomainControllerAffiliation]]    Script Date: 2/15/2017 2:37:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[DomainControllerAffiliation] (
  [dcaKey]                     [varchar](255) NOT NULL,
  [attributesToSync]           [varchar](1024),
  [bindPassword]               [varchar](255),
  [bindUser]                   [varchar](255),
  [highestCommittedUSN]        [bigint],
  [invocationId]               [varchar](255),
  [rootDN]                     [varchar](255),
  [searchDeletedObjectsFilter] [varchar](255),
  [searchFilter]               [varchar](255),
  [syncBaseDN]                 [varchar](255),
  [url]                        [varchar](255),
  CONSTRAINT [PK_DomainControllerAffiliation_V3] PRIMARY KEY CLUSTERED
(
	[dcaKey] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
