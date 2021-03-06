USE [ADUsers]
GO

/****** Object:  Table [dbo].[AD_USER_CL]    Script Date: 2/15/2017 2:37:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[AD_USER_CL](
  [clID] [bigint] IDENTITY(1,1) NOT NULL,
  [objectGUID] [varchar](256) NOT NULL,
	[sAMAccountName] [varchar](256) NOT NULL,
	[givenName] [varchar](256) NULL,
	[sn] [varchar](256) NULL,
	[displayName] [varchar](256) NULL,
	[title] [varchar](512) NULL,
	[mail] [varchar](256) NULL,
	[employeeID] [varchar](512) NULL,
	[SSN] [varchar](512) NULL,
	[manager] [varchar](512) NULL,
	[department] [varchar](512) NULL,
	[accountExpires] [datetime2] NULL,
	[company] [varchar](512) NULL,
	[description] [varchar](512) NULL,
	[distinguishedName] [varchar](512) NOT NULL,
	[homeDirectory] [varchar](512) NULL,
	[homeDrive] [varchar](256) NULL,
	[homeMDB] [varchar](512) NULL,
	[homeMTA] [varchar](512) NULL,
	[DOB] [varchar](512) NULL,
	[info] [varchar](max) NULL,
	[employeeType] [varchar](512) NULL,
	[extensionAttribute4] [varchar](512) NULL,
	[extensionAttribute5] [varchar](512) NULL,
	[extensionAttribute7] [varchar](512) NULL,
	[extensionAttribute10] [varchar](512) NULL,
	[whenChanged] [datetime] NULL,
	[whenCreated] [datetime] NULL,
	[directReports] [varchar](max) NULL,
	[proxyAddresses] [varchar](max) NULL,
	[memberOf] [varchar](max) NULL,
	[uSNChanged] [varchar](256) NOT NULL,
	[uSNCreated] [varchar](256) NOT NULL,
	[userAccountControl] [varchar](256) NULL,
	[userPrincipalName] [varchar](256) NULL,
	[msDSUserAccountDisabled] [varchar](256) NULL,
	[MSOID] [varchar](256) NULL,
	[PCHAID] [varchar](256) NULL,
	[SHCEmployeeID] [varchar](256) NULL,
	[changeType] [varchar](256) NOT NULL,
	[syncedAt] [datetime] NOT NULL,
CONSTRAINT [PK_AD_USER_CL_V4] PRIMARY KEY CLUSTERED
(
	[clID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

