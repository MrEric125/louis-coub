import com.google.gson.Gson;
import com.intellij.database.psi.DbTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public class LouisCodeGenerateAction extends AnAction {
    Gson gson = new Gson();

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        PsiElement data = e.getData(LangDataKeys.PSI_ELEMENT);
        if (data instanceof DbTable) {
            DbTable dbTable = (DbTable) data;
            Project project = e.getProject();

            Messages.showMessageDialog(project, gson.toJson(dbTable), "datasource", Messages.getInformationIcon());

        }
    }
}
