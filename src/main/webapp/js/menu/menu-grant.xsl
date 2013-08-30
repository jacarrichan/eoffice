<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output omit-xml-declaration="yes"/>
	<xsl:strip-space elements="Function"/>
	<xsl:strip-space elements="Items"/>
	<xsl:strip-space elements="Item"/>
    <xsl:template match="node()|@*">
      <xsl:copy>
         <xsl:apply-templates select="node()|@*"/>
      </xsl:copy>
    </xsl:template>
    <xsl:template match="url"/>
    <xsl:template match="Items[@isPublic='true']"/>
    <xsl:template match="Item[@isPublic='true']"/>
</xsl:stylesheet>