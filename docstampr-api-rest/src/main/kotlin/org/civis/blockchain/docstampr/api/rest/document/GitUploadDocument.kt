package org.civis.blockchain.docstampr.api.document

import java.io.FileInputStream

class GitUploadDocument(docstamprGitRepo: String, keyGitRepo: String,
                        var pushBranch: Boolean) {

    val git = GitBaseCommand(docstamprGitRepo, keyGitRepo);

    fun upload(hash: String, filename: String, data: FileInputStream): String {

        git.checkoutBranch(hash)
        git.commitFile(filename, data)
        if(pushBranch) {
            git.pushBranch()
        }
        git.checkoutBranch("master")
        return "https://raw.githubusercontent.com/civis-blockchain/docstampr-file/$hash/${filename}"
    }

}