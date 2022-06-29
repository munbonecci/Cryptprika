package com.munbonecci.cryptprika.common.ui.modal

import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.common.ui.fragment.CustomDialogFragment

class GenericCustomModal(title: Int, message: Int) : CustomDialogFragment() {
    override val dialogTittle = title
    override var dialogMessage = message
    override val dialogIcon: Int
        get() = R.drawable.ic_error_circle
    override val dialogButtonText: Int
        get() = R.string.text_accept
}