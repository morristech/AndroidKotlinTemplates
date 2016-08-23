package com.templates.kotlintemplates.utils

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.ArrayList

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

object XMLUtils {
    // Retorna a tag raiz do XML
    fun getRoot(xml: String, charset: String): Element {
        try {
            var `in`: InputStream? = null
            val factory = DocumentBuilderFactory.newInstance()
            factory.isValidating = false
            val bytes = xml.toByteArray(charset(charset))
            `in` = ByteArrayInputStream(bytes)
            val builder = factory.newDocumentBuilder()
            val dom = builder.parse(`in`)
            val root = dom.documentElement ?: throw RuntimeException("XML invalido")
            return root
        } catch (e: Exception) {
            throw RuntimeException(e.message, e)
        }

    }

    // Retorna o texto dentro da tag
    fun getText(node: Node, tag: String): String {
        val n = getChild(node, tag)
        if (n != null) {
            val text = n.firstChild
            if (text != null) {
                val s = text.nodeValue
                return s.trim { it <= ' ' }
            }
        }
        return ""
    }

    fun getText(n: Node?): String {
        if (n != null) {
            val text = n.firstChild
            if (text != null) {
                val s = text.nodeValue
                return s.trim { it <= ' ' }
            }
        }
        return ""
    }

    // Retorna as tags filhas do node informado
    fun getChildren(node: Node, name: String): List<Node> {
        val children = ArrayList<Node>()
        val nodes = node.childNodes
        if (nodes != null && nodes.length >= 1) {
            for (i in 0..nodes.length - 1) {
                val n = nodes.item(i)
                if (name == n.nodeName) {
                    children.add(n)
                }
            }
        }
        return children
    }

    fun getChild(node: Node?, tag: String): Node? {
        if (node == null) {
            return null
        }
        val childNodes = node.childNodes ?: return null
        for (i in 0..childNodes.length - 1) {
            val item = childNodes.item(i)
            if (item != null) {
                val name = item.nodeName
                if (tag.equals(name, ignoreCase = true)) {
                    return item
                }
            }
        }
        return null
    }
}
