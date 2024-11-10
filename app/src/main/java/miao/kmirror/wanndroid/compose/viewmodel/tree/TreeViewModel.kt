package miao.kmirror.wanndroid.compose.viewmodel.tree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class TreeViewModel : ViewModel() {

    private val _pageState = MutableStateFlow(false)
    val pageState: StateFlow<Boolean> = _pageState

    // 父目录的索引
    private val _parentDirIndex = MutableStateFlow(0)
    val parentDirIndex: StateFlow<Int> = _parentDirIndex

    // 子目录的索引列表
    private val _childDirIndexList = MutableStateFlow<List<Int>>(emptyList())
    val childDirIndexList: StateFlow<List<Int>> = _childDirIndexList

    fun initializeData() {
        viewModelScope.launch {
            _pageState.value = true
            // 初始化父目录索引
            _parentDirIndex.value = 0

            // 初始化子目录索引列表
            val initialChildDirIndexList = ParentDir.testData.map { 0 }
            _childDirIndexList.value = initialChildDirIndexList
            _pageState.value = true
        }
    }

    // 更新父目录索引
    fun updateParentDirIndex(newIndex: Int) {
        _parentDirIndex.value = newIndex
    }

    // 更新子目录索引
    fun updateChildDirIndex(parentIndex: Int, newIndex: Int) {
        val updatedList = _childDirIndexList.value.toMutableList()
        updatedList[parentIndex] = newIndex
        _childDirIndexList.value = updatedList
    }
}

data class ParentDir(val desc: String, val childDirList: List<ChildDir>) {
    data class ChildDir(val desc: String)

    companion object {
        val testData = arrayListOf(
            ParentDir(
                "A", arrayListOf(
                    ChildDir("A1"),
                    ChildDir("A2"),
                    ChildDir("A3"),
                    ChildDir("A4"),
                    ChildDir("A5"),
                    ChildDir("A6"),
                    ChildDir("A7"),
                    ChildDir("A8"),
                )
            ),
            ParentDir(
                "B", arrayListOf(
                    ChildDir("B1"),
                    ChildDir("B2"),
                    ChildDir("B3"),
                    ChildDir("B4"),
                    ChildDir("B5"),
                    ChildDir("B6"),
                    ChildDir("B7"),
                    ChildDir("B8"),
                )
            ),
            ParentDir(
                "C", arrayListOf(
                    ChildDir("C1"),
                    ChildDir("C2"),
                    ChildDir("C3"),
                    ChildDir("C4"),
                    ChildDir("C5"),
                    ChildDir("C6"),
                    ChildDir("C7"),
                    ChildDir("C8"),
                )
            ),
            ParentDir(
                "D", arrayListOf(
                    ChildDir("D1"),
                    ChildDir("D2"),
                    ChildDir("D3"),
                    ChildDir("D4"),
                    ChildDir("D5"),
                    ChildDir("D6"),
                    ChildDir("D7"),
                    ChildDir("D8"),
                )
            ),
            ParentDir(
                "E", arrayListOf(
                    ChildDir("E1"),
                    ChildDir("E2"),
                    ChildDir("E3"),
                    ChildDir("E4"),
                    ChildDir("E5"),
                    ChildDir("E6"),
                    ChildDir("E7"),
                    ChildDir("E8"),
                )
            ),
            ParentDir(
                "F", arrayListOf(
                    ChildDir("F1"),
                    ChildDir("F2"),
                    ChildDir("F3"),
                    ChildDir("F4"),
                    ChildDir("F5"),
                    ChildDir("F6"),
                    ChildDir("F7"),
                    ChildDir("F8"),
                )
            ),
            ParentDir(
                "G", arrayListOf(
                    ChildDir("G1"),
                    ChildDir("G2"),
                    ChildDir("G3"),
                    ChildDir("G4"),
                    ChildDir("G5"),
                    ChildDir("G6"),
                    ChildDir("G7"),
                    ChildDir("G8"),
                )
            ),
            ParentDir(
                "H", arrayListOf(
                    ChildDir("H1"),
                    ChildDir("H2"),
                    ChildDir("H3"),
                    ChildDir("H4"),
                    ChildDir("H5"),
                    ChildDir("H6"),
                    ChildDir("H7"),
                    ChildDir("H8"),
                )
            ),
            ParentDir(
                "I", arrayListOf(
                    ChildDir("I1"),
                    ChildDir("I2"),
                    ChildDir("I3"),
                    ChildDir("I4"),
                    ChildDir("I5"),
                    ChildDir("I6"),
                    ChildDir("I7"),
                    ChildDir("I8"),
                )
            )
        )
    }
}
